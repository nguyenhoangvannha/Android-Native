# Appium Getting Started https://appium.io/docs/en/about-appium/getting-started/index.html

## Installing Appium

Appium can be installed in one of two ways: via NPM or by downloading Appium Desktop, which is a graphical, desktop-based way to launch the Appium server.

- Installation via NPM
Make sure you have not installed Node or Appium with sudo, otherwise you'll run into problems).

```
sudo chown -R $USER /usr/local/lib/node_modules

npm install -g appium
```

- Installation via Desktop App Download https://github.com/appium/appium-desktop/releases
https://github.com/appium/appium-desktop#installing-on-macos

## Driver-Specific Setup
You probably want to use Appium to automate something specific, like an iOS or Android application. Support for the automation of a particular platform is provided by an Appium "driver". 

https://appium.io/docs/en/drivers/ios-xcuitest/index.html

## Verifying the Installation
To verify that all of Appium's dependencies are met you can use appium-doctor. Install it with npm install -g appium-doctor, then run the appium-doctor command, supplying the --ios or --android flags to verify that all of the dependencies are set up correctly.

```
appium-doctor --ios
```

## Appium Clients
List of client libraries with Appium server support

https://github.com/webdriverio/webdriverio

https://webdriver.io/docs/gettingstarted

https://appium.io/docs/en/about-appium/appium-clients/index.html

http://appium.io/downloads

## Starting Appium
Now we can kick up an Appium server, either by running it from the command line like so (assuming the NPM install was successful):

```
appium
```

Appium will listen on a port (the default is 4723). Make sure client to connect to Appium on this port. If you want to change, the port, you can do so by using the -p flag when starting Appium (be sure to check out the full list of server parameters).
https://appium.io/docs/en/writing-running-appium/server-args/index.html

## Running Your First Test
https://github.com/appium/appium/tree/master/sample-code/javascript-webdriverio

- Now we can create our test file, named index.js, and initialize the client object:

``` javascript
const wdio = require("webdriverio");
```

- The next thing we need to do is to start an Appium session. We do this by defining a set of server options and Desired Capabilities, and calling wdio.remote() with them.

https://appium.io/docs/en/writing-running-appium/caps/index.html


```
platformName: the name of the platform to automate
platformVersion: the version of the platform to automate
deviceName: the kind of device to automate
app: the path to the app you want to automate (but use the browserName capability instead in the case of automating a web browser)
automationName: the name of the driver you wish to use
```


``` Javascript
// javascript
const opts = {
  path: '/wd/hub',
  port: 4723,
  capabilities: {
    platformName: "Android",
    platformVersion: "8",
    deviceName: "Android Emulator",
    app: "/path/to/the/downloaded/ApiDemos-debug.apk",
    appPackage: "io.appium.android.apis",
    appActivity: ".view.TextFields",
    automationName: "UiAutomator2"
  }
};

async function main () {
  const client = await wdio.remote(opts);
  const field = await client.$("android.widget.EditText");
  await field.setValue("Hello World!");
  const value = await field.getText();
  assert.strictEqual(value,"Hello World!");
  await client.deleteSession();
}

main();
```

## Build ipa zip
To build your archive you need to:

1 - Set "Generic iOS Device" as the selected device (top left corner near the scheme) instead of a simulator device.

 Select a development team in the Signing & Capabilities editor.

2 - Click on "Product" on the top bar, and then "Archive". This will start building your archive. When it finishes the Organizer will comes up and you will be able to see your archive.

https://docs.saucelabs.com/mobile-apps/automated-testing/ipa-files/

## Which framework do you want to use? (Use arrow keys)
❯ mocha 
  jasmine 
  cucumber 
