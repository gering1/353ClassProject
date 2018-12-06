FROM openjdk:jdk-alpine

COPY MtServer.class /deployments/
COPY ClientHandler.class /deployments/
COPY UserAccountList.class /deployments/
COPY UserAccount.class /deployments/

CMD cd /deployments; java MtServer


