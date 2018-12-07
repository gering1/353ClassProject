FROM openjdk:jdk-alpine

COPY UsernameAndPass.class /deployments/
COPY UsernameAndPass$1.class /deployments/
COPY UsernameAndPass$2.class /deployments/
COPY UsernameAndPass$3.class /deployments/
COPY UsernameAndPass$4.class /deployments/
COPY UsernameAndPass$5.class /deployments/
COPY UsernameAndPass$6.class /deployments/
COPY UsernameAndPass$7.class /deployments/
COPY UsernameAndPass$8.class /deployments/
COPY UsernameAndPass$8$1.class /deployments/
COPY UsernameAndPass$9.class /deployments/
COPY UsernameAndPass$10.class /deployments/
COPY guiClient.class /deployments/
COPY ClientListener.class /deployments/
#COPY User.class /deployments/
COPY UserAccount.class /deployments/
COPY UserAccountList.class /deployments/
COPY SpotifySearch.class /deployments/
COPY commons-logging-1.2.jar /deployments/
COPY gson-2.8.5.jar /deployments/
COPY httpclient-4.5.6.jar /deployments/
COPY httpclient-cache-4.5.6.jar /deployments/
COPY httpcore-4.4.10.jar /deployments/
COPY nv-i18n-1.23.jar /deployments/
COPY spotify-web-api-java-2.1.1.jar /deployments/
#COPY Users /deployments/
 
CMD cd /deployments; java -cp ".:*" UsernameAndPass

