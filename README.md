
# react-native-zlst-rn-utils

## Getting started

`$ npm install react-native-zlst-rn-utils --save`

### Mostly automatic installation

`$ react-native link react-native-zlst-rn-utils`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-zlst-rn-utils` and add `RNZlstRnUtils.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNZlstRnUtils.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.zlst.rn.RNZlstRnUtilsPackage;` to the imports at the top of the file
  - Add `new RNZlstRnUtilsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-zlst-rn-utils'
  	project(':react-native-zlst-rn-utils').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-zlst-rn-utils/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-zlst-rn-utils')
  	```


## Usage
```javascript
import RNZlstRnUtils from 'react-native-zlst-rn-utils';

// TODO: What to do with the module?
RNZlstRnUtils;
```
  