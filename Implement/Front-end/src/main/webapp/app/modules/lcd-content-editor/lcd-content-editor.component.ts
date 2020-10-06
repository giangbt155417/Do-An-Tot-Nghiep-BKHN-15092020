import { ChangeDetectorRef, Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { DialogService } from 'app/services/dialog.service';
import { LCDTemplateGroup } from '../../entities/lcd-template-group';
import { LCDTemplate } from '../../entities/lcd-template';
import { ContentEditorTools } from 'app/utils/constants';

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
  contentGroupSelected: any;
  contentSelected: any;
  toolSelected: ContentEditorTools = ContentEditorTools.SELECT_AREA;
  contentGroupsView: Array<LCDTemplateGroup> = new Array<LCDTemplateGroup>();
  contentsView: Array<LCDTemplate> = new Array<LCDTemplate>();

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
    this.changeDetectorRef.detectChanges();
    const myNode = document.getElementById('canvasContainer');
    while (myNode !== null && myNode.firstChild) {
      myNode.removeChild(myNode.firstChild);
    }
    this.renderer.setStyle(myNode, 'transform', 'scale(' + 0.5 + ',' + 0.5 + ')');
    this.createCanvasTemplate(content);
    this.createCanvasLayoutRealTime(content);
  }

  public selectTools(tool: ContentEditorTools) {
    this.toolSelected = tool;
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
}
