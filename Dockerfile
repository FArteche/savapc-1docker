# Use a imagem oficial do Tomcat como base
FROM tomcat:latest

# Copie o arquivo WAR para o diretório webapps do Tomcat
ADD target/savapc-1.war /usr/local/tomcat/webapps/

# Exponha a porta 8080 para o Tomcat
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]
