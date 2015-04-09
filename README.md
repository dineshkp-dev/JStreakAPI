# JStreakAPI
A Java based client implementation using HttpComponents for Streak(https://www.streak.com).

Streak is a CRM (Customer Relationship Management) Sofware that integrates seamlessly with GMail and provides an excellent way to easily manage complex tasks from Customer Acquistion from a Sales perspective to simple projects like a Holiday Planning!
Streak, has released its APIs to be used for customized solutions at https://www.streak.com/api/ , which can be used to get/create/update/delete items on Streak. I had not seen a Java-based implementation for Streak's API, hence decided to come up 
with my own solution that would help future developers to easily develop applications.

From a Java perspective, I have tried to the modules in the project implementation as cohesive as possible, with the lowest possible coupling.

# 1. Libraries Used:
<ol>
	<li>Jackson - for JSON parsing/encoding</li>
	<li>Http Components - for Querying/Adding/Deleting/Updating through HTTPS(including authentication)</li>
	<li>Maven - build script to get libraries and build a JAR file</li>
	<li>PowerMock(with JUnit & Mockito) - For Unit Testing</li>
</ol>
# 2. Build/Compile the project:
The 'pom.xml' for Maven is already been setup with the necessary 'maven-assembly-plugin' to create a fat-jar file.
<ol>
	<li>Download and install Eclipse from https://eclipse.org/downloads/ , if already installed skip to Step 2. below</li>
	<li>Within Eclipse, install the 'm2e' plugin for required for Maven projects</li>
		<ul>
			<li>Open Eclipse, and navigate to 'Help' -> 'Install new Software...'</li>
			<li>Enter 'http://download.eclipse.org/technology/m2e/releases'at the 'Work With' Text box</li>
			<li>Once the below Box is populated, select 'Maven Integration for Eclipse' and click 'Finish' to install m2e-plugin</li>
		</ul>
</ol>
<ol start=3>
	<li>Set-up the 'Run-Configuration' to 'clean compile package'</li>
	<li>Use the JAR file found in : {projectDir}/target/jstreak-{version.no}-jar-with-dependencies.jar</li>
</ol>
# 3. Usage:
Note: Currently the final build Jar file is not hosted  on any site, it would be good if you could suggest me a site to host it, so that users can start using it directly.
<p><h5>You can download the compiled (fat) JAR file from my implementation Project at: 
<a href="https://github.com/dingy007/StreakIntegrationAPI/tree/master/src/main/resources" target="_blank">Compiled JAR file for Use!</a> </h5>
Please refer to Step 2. above, on how to build and generate the JAR file (it is really easy!)

(Coming up soon... <h5>checkout: <a href="https://github.com/dingy007/StreakIntegrationAPI" target="_blank">How to use JStreakAPI</a> for a Sample implementation!</h5>)
