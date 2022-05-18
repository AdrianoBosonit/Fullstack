docker-compose up -d

docker tag backempresadocker adrianobravoguzman/backempresadocker
docker tag backweb1docker adrianobravoguzman/backweb1docker
docker tag backweb2docker adrianobravoguzman/backweb2docker
docker tag balanceador adrianobravoguzman/balanceador
docker tag eureka adrianobravoguzman/eureka
docker tag confluentinc/cp-zookeeper adrianobravoguzman/zookeeper
docker tag confluentinc/cp-kafka adrianobravoguzman/kafka
docker tag postgres adrianobravoguzman/postgres


docker push adrianobravoguzman/eureka
docker push adrianobravoguzman/balanceador
docker push adrianobravoguzman/zookeeper
docker push adrianobravoguzman/kafka
docker push adrianobravoguzman/postgres
docker push adrianobravoguzman/backempresa
docker push adrianobravoguzman/backweb2docker
docker push adrianobravoguzman/backweb1docker