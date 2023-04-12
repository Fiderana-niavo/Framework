export CLASSPATH=.:/home/fiderana/servlet-api.jar
source ~/.bashrc
javac -cp servlet-api.jar -d . *.java
jar -cvf /home/fiderana/fw.jar etu1917
cp  /home/fiderana/fw.jar WEB-INF/lib
jar -cvf WEB-INF.war WEB-INF
cp WEB-INF.war /opt/apache-tomcat-10.0.27/webapps/projet_test
