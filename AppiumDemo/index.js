// // javascript

const wdio = require("webdriverio");

const opts = {
  path: '/wd/hub',
  port: 4723,
  capabilities: {
    platformName: "Android",
    // appPackage: "com.nhvn.todoandroidnative",
    appActivity: ".ui.elements.MainActivity",
    automationName: "Espresso",
    app: "/Users/nguyenhoangvannha/Documents/projects/Android-Native/TodoAndroidNative/app/build/outputs/apk/debug/app-debug.apk",
  }
};

async function main() {
  const driver = await wdio.remote(opts);

  driver.setSetting("driver", "compose");

  let addTodoFloatingActionButton = await driver.elementByTagName('AddTodoFloatingActionButton');
  addTodoFloatingActionButton.click();

  let editTodoScreen = await driver.elementByTagName('EditTodoScreen');
  editTodoScreen.isDisplayed();

  let editTodoTitle = await driver.elementByTagName('EditTodoTitle');
  editTodoTitle.sendKeys(`Todo App ${Date()} EditTodoTitle`);

  let editTodoDescription = await driver.elementByTagName('EditTodoDescription');
  editTodoDescription.sendKeys(`Todo App ${Date()} EditTodoDescription`);

  let editTodoSaveButton = await driver.elementByTagName('EditTodoSaveButton');
  editTodoSaveButton.click();
}

main();


// const opts = {
//   path: '/wd/hub',
//   port: 4723,
//   capabilities: {
//     platformName: "Android",
//     // platformVersion: "10",
//     // deviceName: "Pixel 3a XL API 29",
//     appPackage: "com.nhvn.todoandroidnative",
//     // app: "/Users/nguyenhoangvannha/Documents/projects/Android-Native/TodoAndroidNative/app/build/outputs/apk/debug/app-debug.apk",
//     // app: "/Users/nguyenhoangvannha/Documents/projects/Android-Native/AppiumDemo/compose_playground.apk",
//     automationName: "Espresso",
//     // forceEspressoRebuild: true,
//     // // espressoBuildConfig: '{"additionalAndroidTestDependencies": ' +
//     // // '"androidx.activity:activity:1.4.0", ' +
//     // // '"androidx.fragment:fragment:1.4.0"]}',
//     // // espressoBuildConfig: ‘{“additionalAndroidTestDependencies”: [“androidx.lifecycle:lifecycle-extensions:<version>”, “androidx.activity:activity:<version>”, “androidx.fragment:fragment:<version>”]}’
//     // espressoBuildConfig: '{"toolsVersions": { "compileSdk": "31", "minSdk": "26"},"additionalAndroidTestDependencies": ' +
//     //   '["androidx.lifecycle:lifecycle-extensions:2.2.0", ' +
//     //   '"androidx.activity:activity:1.4.0", ' +
//     //   '"androidx.fragment:fragment:1.4.0"]}',
//   }
// };

// async function main () {
//   let driver
//   try {
//    driver = await wdio.remote(opts);
//   } catch (error) {
//     console.log("error", error)
//   }
// console.log("aaee")
// // let el = await driver.elementByXPath("//*[@text='Text Input Components']");
// // await driver.moveTo(el);
// // await el.click();

// //  await driver.updateSettings({ driver: 'compose' });
// //  driver.setSetting(“driver”, “compose”);
// // driver.setSetting(“driver”, “espresso”);


// let textElement = await driver.elementByTagName('text_input');
// // verify default text
// await textElement.text().should.eventually.equal('Enter your text here');

// await textElement.setImmediateValue(['hello']);
// // should append to the exiting text
// await driver.elementByTagName('text_input').text().should.eventually.equal('Enter your text herehello');

// textElement.setText(['テスト']);
// //  should replace existing text
// await textElement.text().should.eventually.equal('テスト');

// textElement.clear();
// //  should clear existing text
// await textElement.text().should.eventually.equal('');

//   await driver.deleteSession();
// }

// main ()