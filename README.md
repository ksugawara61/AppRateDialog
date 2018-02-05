# AppRateDialog

AppRateDialog library can show Rate Dialog easily.

[![](https://jitpack.io/v/ksugawara61/AppRateDialog.svg)](https://jitpack.io/#ksugawara61/AppRateDialog)

## Implementation

To use this library your `minSdkVersion` must be >= 17

Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}   
```

Add the dependency

```
	dependencies {
	        compile 'com.github.ksugawara61:AppRateDialog:1.0.0'
	}
```

## Usage

Add Your Application Class.

```
        AppRateDialog.with(this)
                .setShowDialogCount(10)
                .setRemindDialogInterval(5)
                .setOpinionUri("set your uri")
                .setReviewUri("set your app uri");
```

Add Your MainActivity Class.

```
        AppRateDialog.addLaunchCount(this);
        AppRateDialog.showSatisfactionDialog(this);
```

## License

```
MIT License

Copyright (c) 2018 ksugawara61

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
