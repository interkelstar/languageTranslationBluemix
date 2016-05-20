## About

Web app for translation text and generate speech afterwards, built on Bluemix services.

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy)

## Usage

Choose source language in the input dropdown and fill in the text you want to translate. Then choose target language from the output dropdown. 
Notice, that the list of target languages depends on source language you selected. Click Translate button and you will see translation in the output area.
When text is translated you can click Speak button and hear translated text. No all languages available to speech service, so sometimes Speak button will be disabled.

## REST API Usage

----
  Returns json data with translated text.

* **URL**

  /translate

* **Method:**

  `POST`
  
*  **Request Params**
 
   `from=[string]`
   
   `to=[string]`

* **Data Params**

  {
    "text": string
  }

* **Response:**

  * **Code:** 200 <br />
    **Content:** `{ text : "Hola" }`
    
* **Simple call:**
    ```
    $.ajax({
      type: "POST",
      url: "/translate?from=en&to=es",
      data: JSON.stringify({
                "text": "Hello"
            }),
      contentType: "application/json"
    }).done(function(data) {
        console.log(data.text);
    }
    ```
    
----
  Returns wave audio stream with synthesized text.

* **URL**

  /textToSpeech

* **Method:**

  `GET`
  
*  **Request Params**
 
   `text=[string]`
   
   `lang=[string]`

* **Response:**

  * **Code:** 200 <br />
    **Content:** audio/wav source
    
* **Simple call:**
    ```
    $.ajax({
      type: "GET",
      url: "/textToSpeech",
      data: {
        lang: "en",
        text: "Hello"
      }
    }).done(function(data) {
        console.log(data.text); //array of bytes
    }
    ```
* **Simple usage:**
    `<audio src="/textToSpeech?lang=en&text=Hello></audio>`

## Building and Deploying

This project uses Maven to build and deploy the app, so you must have Maven installed and configured on
your machine.  You can follow the simple [instructions](http://maven.apache.org/download.cgi) out on the 
Maven site to install Maven.

Once you have Maven installed, run this command from the root of the project to build the code.

    mvn package

To run the application locally run

    mvn -P run

Then open your favorite browser and navigate to http://localhost:8080.

To deploy the application to Bluemix follow instructions below

1. Create a Bluemix Account

    Sign up in Bluemix, or use an existing account. Watson Services in Beta are free to use.

2. Download and install the Cloud-foundry CLI tool

3. Edit the `manifest.yml` file and change the `<application-name>` to something unique.
  ```none
  declared-services:
  language-translation-service:
    label: language_translation
    plan: standard
  text-to-speech-service:
    label: text_to_speech
    plan: standard
applications:
- name: <application-name>
  memory: 512M
  instances: 1
  path: target/<application-name>-0.0.1-SNAPSHOT.jar
  domain: mybluemix.net
  host: <application-name>
  services:
    - language-translation-service
    - text-to-speech-service
  ```
  The name you use will determinate your application url initially, e.g. `<application-name>.mybluemix.net`.

4. Connect to Bluemix in the command line tool.
  ```sh
  $ cf api https://api.ng.bluemix.net
  $ cf login -u <your user ID>
  ```

5. Create the Text to Speech service in Bluemix.
  ```sh
  $ cf create-service text_to_speech standard text-to-speech-service
  ```

6. Push it live!
  ```sh
  $ cf push
  ```
## Dependencies

For sever side dependencies see the pom.xml file in the root of the repository.

Client side dependencies can be found below.

* [Bootstrap 3.1.1](http://getbootstrap.com/)
* [JQuery 1.11](http://jquery.com/)
