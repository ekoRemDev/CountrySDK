# CountrySDK [ ![Download](https://api.bintray.com/packages/vaghelamithun/maven/country-sdk/images/download.svg) ](https://bintray.com/vaghelamithun/maven/country-sdk/_latestVersion)

Include countries related data like, flag, name, code, dial code, population, etc. where developer can easily integrate and use it.

Country List             |  Country Grid              | Country Dialog        | Country Details   
:-------------------------:|:-------------------------:|:----------------------:|:---------------------:
<img src="https://github.com/riontech-xten/CountrySDK/blob/master/list.png" height="350" alt="Country List" style="float:left"/>  |  <img src="https://github.com/riontech-xten/CountrySDK/blob/master/grid.png" height="350" alt="Country Grid" style="float:left"/>  |  <img src="https://github.com/riontech-xten/CountrySDK/blob/master/dialog.png" height="350" alt="Country Dialog" style="float:left"/>  |  <img src="https://github.com/riontech-xten/CountrySDK/blob/master/details.png" height="350" alt="Country Details" style="float:left"/>

### Dependency Setup
To use **CountrySdk** in your projects, simply add the library as a dependency to your build.

##### Maven
```
<dependency>
  <groupId>com.xtensolutions.country</groupId>
  <artifactId>country-sdk</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
##### Gradle
```
dependencies {
  compile 'com.xtensolutions.country:country-sdk:1.0.1'
}
```

### Application Setup
Just initialize the **CountrySdk** into ```onCreate``` method of your application class like below example

``` 
public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CountrySDK.initialize(this);
    }
}

```

The library is currently configured to be built via Gradle only. It has the following dependencies:

* Android Gradle plugin 2.1.0 - com.android.tools.build:gradle:3.0.1
* Android Support appcompt-v7 - com.android.support:appcompat-v7:26.1.0
* Compiled SDK Version        - Oreo-26
* Supported Version           - >= 2.1

### How to use?
For more detailed code example to use the library, Please refere the `/sample` app.

After initialize sdk you can direcly call ```getAllCountry``` method of ```CountryUtils``` class in any class or activity.
# Listing
```
ArrayList<Country> countries = CountryUtils.getAllCountry(this);

```
# Dialog
```
CountryDialog dialog = new CountryDialog();
dialog.setOnCountryClickListener(countryClickListener);
dialog.show(getSupportFragmentManager(), "Select Country");
```

To access the click event of dialog item, simply implement the below listener.

```
private CountryDialog.OnCountryClickListener countryClickListener = new CountryDialog.OnCountryClickListener() {
        @Override
        public void onCountrySelected(Country country) {
            setHeaderView(country);
            countryDetailsView.setUpDetailsView(country, rlRootView);
        }
};
```

### Licence
```
Copyright (c) 2016 riontech-xten

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```




