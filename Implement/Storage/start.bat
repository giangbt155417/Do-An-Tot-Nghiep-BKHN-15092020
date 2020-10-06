@echo off
echo Starting MinIO server...
docker run -p 9200:9000 -v %cd%:/data -e "MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE" -e "MINIO_SECRET_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY" minio/minio:RELEASE.2020-06-22T03-12-50Z server /data
pause