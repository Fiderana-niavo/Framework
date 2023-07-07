export CLASSPATH=.:/home/fiderana/servlet-api.jar
source ~/.bashrc
javac -cp servlet-api.jar -parameters -d . *.java
jar -cvf /home/fiderana/fw.jar etu1917
cp  /home/fiderana/fw.jar Test/WEB-INF/lib
