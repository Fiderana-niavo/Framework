export CLASSPATH=.:/home/fiderana/servlet-api.jar
source ~/.bashrc
javac -cp servlet-api.jar -d . *.java
jar -cvf /home/fiderana/fw.jar etu1917
cp  /home/fiderana/fw.jar Test/WEB-INF/lib
cd Test
jar -cvf TestFramework.war -C *
cp TestFramework.war /opt/apache-tomcat-10.0.27/webapps/
