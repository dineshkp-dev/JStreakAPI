# JStreakAPI
A Java based client implementation using HttpComponents for Streak(https://www.streak.com).

Streak is a CRM (Customer Relationship Management) Sofware that integrates seamlessly with GMail and provides an excellent way to easily manage complex tasks from Customer Acquistion from a Sales perspective to simple projects like a Holiday Planning!
Streak, has released its APIs to be used for customized solutions at https://www.streak.com/api/ , which can be used to get/create/update/delete items on Streak. I had not seen a Java-based implementation for Streak's API, hence decided to come up 
with my own solution that would help future developers to easily develop applications.

From a Java perspective, I have tried to the modules in the project implementation as cohesive as possible, with the lowest possible coupling.

# Libraries Used:
<ol>
<li>Jackson - for JSON parsing/encoding</li>
<li>Http Components - for Querying/Adding/Deleting/Updating through HTTPS(including authentication)</li>
<li>Maven - build script to get libraries and build a JAR file</li>
<li>PowerMock(with JUnit & Mockito) - For Unit Testing</li>
</ol>
# Usage
Currently the final build Jar file is not hosted  on any site, it would be good if you could suggest me a site to host it, so that users can use it easily.
(Coming up soon... checkout: https://github.com/dingy007/StreakIntegrationAPI for a Sample implementation!)
