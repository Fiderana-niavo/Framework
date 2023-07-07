export CLASSPATH=.:/home/fiderana/servlet-api.jar
source ~/.bashrc
cd Test
jar -cvf TestFramework.war -C *
cp TestFramework.war /opt/apache-tomcat-10.0.27/webapps/