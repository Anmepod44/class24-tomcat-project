**# Installing and Running Tomcat 11 Locally

This guide continues from the step where you have already downloaded the Tomcat 11 archive using wget.

## 1. Extract the archive

tar -xzf apache-tomcat-11.0.10.tar.gz

Explanation: The tar -xzf command extracts the contents of the .tar.gz archive. After this step, you’ll see a new folder apache-tomcat-11.0.10/ in your current directory.

## 2. Move Tomcat to a standard location

mv apache-tomcat-11.0.10 ~/tomcat11

Explanation: Moving the extracted folder to your home directory (renamed to tomcat11) makes it easier to reference and manage. You can choose another location if you prefer, but ~/tomcat11 keeps it clean.

## 3. Grant execute permissions

chmod +x ~/tomcat11/bin/*.sh

Explanation: The startup and shutdown scripts inside the bin/ directory need execution permissions. This command ensures you can run them without issues.

## 4. Start Tomcat

~/tomcat11/bin/startup.sh

Explanation: Runs the startup.sh script, which launches Tomcat as a background process. Once started, Tomcat will listen on port 8080 by default.

Check the logs to confirm it started successfully:

tail -f ~/tomcat11/logs/catalina.out

## 5. Access Tomcat in the browser

Open your web browser and go to:

http://localhost:8080

Explanation: If Tomcat started correctly, you should see the default Tomcat welcome page.

## 6. Deploy your application (WAR file)

If you built your Maven project and have a WAR file:

cp target/nes-academy-class24.war ~/tomcat11/webapps/

Explanation: Copying the WAR file into webapps/ makes Tomcat automatically unpack and deploy it. The app will then be available at:

http://localhost:8080/nes-academy-class24/

http://localhost:8080/nes-academy-class24/home

## 7. Stop Tomcat

~/tomcat11/bin/shutdown.sh

Explanation: Runs the shutdown.sh script, gracefully stopping the Tomcat server and freeing up port 8080.

### Summary

* Download: wget
* Extract: tar -xzf
* Move: place Tomcat somewhere convenient
* Permissions: enable execution for scripts
* Start/Stop: use startup.sh and shutdown.sh
* Deploy: drop WAR files into webapps/

This setup gives you a fully working local Tomcat 11 environment where you can run and test your Maven web projects.

# Setting up a Tomcat Manager User

Follow these commands and steps to create a username and password for Tomcat’s Manager application.

## 1. Open the tomcat-users.xml configuration file

nano ~/tomcat11/conf/tomcat-users.xml

## 2. Add a Manager user

Inside the `<tomcat-users>` element, add:

<tomcat-users>

<!-- Roles -->

<role rolename="manager-gui"/>

<role rolename="manager-script"/>

<role rolename="admin-gui"/>

<role rolename="admin-script"/>

<!-- User -->

<user username="deployer" password="StrongPass!" roles="manager-gui,manager-script,admin-gui,admin-script"/>

</tomcat-users>

## 3. Restart Tomcat to apply changes

~/tomcat11/bin/shutdown.sh

~/tomcat11/bin/startup.sh

## 4. Test your credentials

Log in to the Tomcat Manager web UI:

 http://localhost:8080/manager/html

* Username: deployer
  Password: StrongPass!

Or deploy via API:

 curl -u deployer:StrongPass! \

  --upload-file target/nes-academy-class24.war \

  "http://localhost:8080/manager/text/deploy?path=/class24&update=true"

**
