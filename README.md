# new era solutions academy class24

A clean Maven WAR project for Tomcat demonstrating clear separation between Java (Servlet) and static UI (HTML/CSS/JS).

## Build
```bash
mvn clean package
```
Result: `target/nes-academy-class24.war`

## Deploy (Tomcat)
Copy WAR into Tomcat's `webapps/` or use the Manager API.

```bash
scp target/nes-academy-class24.war ubuntu@<ec2>:/opt/tomcat/webapps/
# or
curl -u $TUSER:$TPASS --upload-file target/nes-academy-class24.war \
  "http://<EC2-IP>:8080/manager/text/deploy?path=/class24&update=true"
```

## Browse
- `http://<EC2-IP>:8080/class24/`
- `http://<EC2-IP>:8080/class24/home` (servlet redirect → index.html)
