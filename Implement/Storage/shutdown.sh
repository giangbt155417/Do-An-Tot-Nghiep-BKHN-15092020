@echo OFF
docker container ls
echo ''
read -p 'Enter MinIO container ID: ' containerid
docker container rm -f $containerid
read -n1 -r -p 'MinIO server shut down completed!' key
