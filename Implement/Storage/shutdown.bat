@echo OFF
docker container ls
echo[
set /p containerId=Enter MinIO container ID:
docker container rm -f %containerId%
echo MinIO server shut down completed!
pause
