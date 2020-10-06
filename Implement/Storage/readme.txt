Prequisite: Install Docker

Run start.bat to start MinIO server
Run shutdown.bat, enter the container ID of MinIO server to shutdown

(For demo purpose only)
MinIO access key: AKIAIOSFODNN7EXAMPLE
MinIO secret key: wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY

To view the files, go to: http://localhost:9200, enter the keys above.

In the first time of running, MinIO may ask to be shared a folder from host file system to mount the persistent storage.
Please feel free to allow that. 

Also, CMP needs permission from from MinIO to access data, so please add a policy of "Read & Write" for each bucket.