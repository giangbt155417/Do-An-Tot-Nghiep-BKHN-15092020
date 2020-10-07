import { ChangeDetectorRef, Component, ElementRef, HostListener, OnInit, Renderer2, ViewChild } from '@angular/core';
import { DialogService } from 'app/services/dialog.service';
import { LCDTemplateGroup } from '../../entities/lcd-template-group';
import { LCDTemplate } from '../../entities/lcd-template';
import { ContentEditorTools } from 'app/utils/constants';
import Panzoom from '@panzoom/panzoom';
import { PanzoomObject } from '@panzoom/panzoom/dist/src/types';
import { Scale } from '../../entities/scale';

@Component({
  selector: 'app-lcd-content-editor',
  templateUrl: './lcd-content-editor.component.html',
  styleUrls: ['./lcd-content-editor.component.scss'],
})
export class LcdContentEditorComponent implements OnInit {
  ContentEditorTools = ContentEditorTools;

  isNoData: boolean = false;
  isViewContents: boolean = false;
  isContentEditor: boolean = false;
  isMouseDown: boolean = false;
  contentGroupSelected: any;
  contentSelected: any;
  toolSelected: ContentEditorTools = ContentEditorTools.SELECT_AREA;
  contentGroupsView: Array<LCDTemplateGroup> = new Array<LCDTemplateGroup>();
  contentsView: Array<LCDTemplate> = new Array<LCDTemplate>();
  panzoom!: PanzoomObject;
  scalePreview: Scale = new Scale('50', 0.5);

  @ViewChild('canvasContainer', { static: false })
  canvasContainer: ElementRef | undefined;

  constructor(private dialogService: DialogService, private renderer: Renderer2, private changeDetectorRef: ChangeDetectorRef) {}

  ngOnInit(): void {
    for (let index = 0; index < 10; index++) {
      let contentGroup = new LCDTemplateGroup('UW', 1920, 540, 'GiangBT', '20200520', 1);
      this.contentGroupsView.push(contentGroup);
    }
    for (let index = 0; index < 10; index++) {
      let content = new LCDTemplate('Normal', 1920, 540, '20200520', 1);
      this.contentsView.push(content);
    }
  }

  public createContentGroup() {}

  public editContentGroup() {}

  public deleteContentGroup() {}

  public selectContentGroup(contentGroup: any) {
    this.contentGroupSelected = contentGroup;
  }

  public viewContents() {
    this.isViewContents = true;
  }

  public backToContentGroups() {
    this.isViewContents = false;
    this.contentSelected = undefined;
    this.contentGroupSelected = undefined;
  }

  public createContent() {}

  public editContent() {}

  public deleteContent() {}

  public selectContent(content: any) {
    this.contentSelected = content;
  }

  public goToContentEditor(content: any) {
    this.isContentEditor = true;
    this.toolSelected = ContentEditorTools.SELECT_AREA;
    this.changeDetectorRef.detectChanges();
    const myNode = document.getElementById('canvasContainer');
    while (myNode !== null && myNode.firstChild) {
      myNode.removeChild(myNode.firstChild);
    }
    // Create Panzoom
    this.scalePreview = { name: '50', value: 0.5 };
    if (this.panzoom != undefined) {
      this.panzoom.setOptions({ disablePan: true, disableZoom: true, force: true });
    }
    this.panzoom = Panzoom(this.canvasContainer?.nativeElement, { startScale: this.scalePreview.value, minScale: 0.25, maxScale: 2 });
    this.panzoom.setOptions({ disableZoom: true, disablePan: true, force: true });
    this.renderer.setStyle(this.canvasContainer?.nativeElement, 'cursor', 'default');
    this.createCanvasTemplate(content);
    this.createCanvasLayoutRealTime(content);
  }

  /**
   * create background canvas preview
   * @param content
   */
  private createCanvasTemplate(content: LCDTemplate) {
    const canvas = this.renderer.createElement('canvas');
    canvas.style.position = 'absolute';
    canvas.style.background = '#000';
    canvas.style.width = content.width + 'px';
    canvas.style.height = content.height + 'px';
    canvas.width = content.width;
    canvas.height = content.height;
    this.renderer.appendChild(this.canvasContainer?.nativeElement, canvas);
  }

  /**
   * create canvas to draw border area when create area
   * @param content
   */
  private createCanvasLayoutRealTime(content: LCDTemplate) {
    const canvas = this.renderer.createElement('canvas');
    canvas.style.position = 'absolute';
    canvas.style.zIndex = '950';
    canvas.style.background = 'transparent';
    canvas.id = 'canvasLayoutRealTime';
    canvas.style.width = content.width + 'px';
    canvas.style.height = content.height + 'px';
    canvas.width = content.width;
    canvas.height = content.height;
    this.renderer.appendChild(this.canvasContainer?.nativeElement, canvas);
  }

  public selectTools(tool: ContentEditorTools) {
    this.toolSelected = tool;
    switch (tool) {
      case ContentEditorTools.SELECT_AREA:
        if (this.panzoom != undefined) {
          this.panzoom.setOptions({ disablePan: true, disableZoom: true, force: true });
        }
        this.renderer.setStyle(this.canvasContainer?.nativeElement, 'cursor', 'default');
        break;
      case ContentEditorTools.DRAW_FIX_TEXT:
      case ContentEditorTools.DRAW_LINK_TEXT:
      case ContentEditorTools.DRAW_FIX_PICTURE:
      case ContentEditorTools.DRAW_LINK_PICTURE:
        if (this.panzoom != undefined) {
          this.panzoom.setOptions({ disablePan: true, disableZoom: true, force: true });
        }
        this.renderer.setStyle(this.canvasContainer?.nativeElement, 'cursor', 'crosshair');
        break;
      case ContentEditorTools.ZOOM_PAN:
        if (this.panzoom != undefined) {
          this.panzoom.setOptions({ disablePan: false, disableZoom: false, force: true });
        }
        this.renderer.setStyle(this.canvasContainer?.nativeElement, 'cursor', 'move');
        break;
      default:
        break;
    }
  }

  pointDownFirst = 0;
  /**
   * mouse down subscriber
   * @param e
   */
  @HostListener('mousedown', ['$event'])
  mouseDown(e: any) {
    if (e.target.id != 'canvasLayoutRealTime') {
      return;
    }
    this.isMouseDown = true;
    this.pointDownFirst = e;
    switch (this.toolSelected) {
      case ContentEditorTools.SELECT_AREA:
        break;
      case ContentEditorTools.DRAW_FIX_TEXT:
      case ContentEditorTools.DRAW_LINK_TEXT:
      case ContentEditorTools.DRAW_FIX_PICTURE:
      case ContentEditorTools.DRAW_LINK_PICTURE:
        break;
      case ContentEditorTools.ZOOM_PAN:
        break;
      default:
        break;
    }
  }

  pointDragStart!: MouseEvent;
  /**
   * mouse down subscriber
   * @param e
   */
  @HostListener('mouseup', ['$event'])
  mouseUp(e: any) {
    if (!this.isMouseDown) {
      return;
    }
    this.isMouseDown = false;
    switch (this.toolSelected) {
      case ContentEditorTools.SELECT_AREA:
        break;
      case ContentEditorTools.DRAW_FIX_TEXT:
      case ContentEditorTools.DRAW_LINK_TEXT:
      case ContentEditorTools.DRAW_FIX_PICTURE:
      case ContentEditorTools.DRAW_LINK_PICTURE:
        break;
      case ContentEditorTools.ZOOM_PAN:
        break;
      default:
        break;
    }
  }

  /**
   * mouse move subscriber
   * @param e
   */
  @HostListener('mousemove', ['$event'])
  mouseMove(e: any) {
    if (!this.isMouseDown) {
      return;
    }
    switch (this.toolSelected) {
      case ContentEditorTools.SELECT_AREA:
        // // pan area
        // if (this.isKeyDownPanArea) {
        //   this.renderer.setStyle(this.canvasContainer?.nativeElement, "cursor", "grabbing");
        //   this.panArea(e);
        // }
        break;
      case ContentEditorTools.DRAW_FIX_TEXT:
      case ContentEditorTools.DRAW_LINK_TEXT:
      case ContentEditorTools.DRAW_FIX_PICTURE:
      case ContentEditorTools.DRAW_LINK_PICTURE:
        // // pan area
        // if (this.isKeyDownPanArea) {
        //   this.renderer.setStyle(this.canvasContainer?.nativeElement, "cursor", "grabbing");
        //   this.panArea(e);
        //   return;
        // }
        // this.createAreaUsingMouse(e, this.toolEditTemplateSelected);
        break;
      case ContentEditorTools.ZOOM_PAN:
        // // pan area
        // if (this.isKeyDownPanArea) {
        //   this.renderer.setStyle(this.canvasContainer?.nativeElement, "cursor", "grabbing");
        //   this.panArea(e);
        // }
        break;
      default:
        break;
    }
  }

  @HostListener('mousewheel', ['$event'])
  mouseWheel(e: any) {
    if (e.target.id != 'canvasLayoutRealTime') {
      return;
    }
    switch (this.toolSelected) {
      case ContentEditorTools.ZOOM_PAN:
        this.panzoom.zoomWithWheel(e);
        this.scalePreview.value = this.panzoom.getScale();
        var scaleName = String(Math.ceil(this.scalePreview.value * 100));
        this.scalePreview.name = scaleName;
        break;
    }
  }

  public changeScalePreview(value: any) {
    this.scalePreview.name = value;
    this.scalePreview.value = Number(value) / 100;
    // Reset panzoom
    if (this.panzoom) {
      this.panzoom.reset({
        startScale: this.scalePreview.value,
        minScale: 0.25,
        maxScale: 2,
        startX: this.panzoom.getPan().x,
        startY: this.panzoom.getPan().y,
      });
    }
  }
}
