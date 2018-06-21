# 👏 MediumClap-Android
[ ![Download](https://api.bintray.com/packages/wajahatkarim3/ClapFab/com.wajahatkarim3.clapfab/images/download.svg) ](https://bintray.com/wajahatkarim3/ClapFab/com.wajahatkarim3.clapfab/_latestVersion) [![](https://img.shields.io/badge/MaterialUp-MediumClap-yellowgreen.svg)](https://www.uplabs.com/posts/medium-clap-android) [![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21) [![Say Thanks!](https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg)](https://saythanks.io/to/wajahatkarim3)

A Custom Floating Action Button (FAB) library like clapping effect on Medium

![](https://raw.githubusercontent.com/wajahatkarim3/MediumClap-Android/master/art/demo_2.gif)

### 📄 How-To Article
Coming soon!

## ✔️ Changelog
Changes exist in the [releases](https://github.com/wajahatkarim3/MediumClap-Android/releases) tab.

## 💻 Installation
Add this in your app's build.gradle file:
```groovy
dependencies {
  implementation 'com.wajahatkarim3.clapfab:clapfab:1.0.0'
}
```

Or add ClapFab as a new dependency inside your pom.xml

```xml
<dependency>
  <groupId>com.wajahatkarim3.clapfab</groupId>
  <artifactId>clapfab</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## ❔ Usage

```xml
<com.wajahatkarim3.clapfab.ClapFAB
        android:id="@+id/clapFAB"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginTop="8dp"
        app:cf_count_circle_color="@color/colorAccent"
        app:cf_count_text_color="@color/white_color"
        app:cf_default_icon="@drawable/ic_star_border_black_24dp"
        app:cf_default_icon_color="@color/colorAccent"
        app:cf_dots_1_color="@android:color/holo_green_dark"
        app:cf_dots_2_color="@color/colorAccent"
        app:cf_filled_icon="@drawable/ic_star_black_24dp"
        app:cf_filled_icon_color="@color/colorAccent"
        app:cf_max_clap_count="20" />
```

## 🎨 Customization and Attributes

All customizable attributes for ClapFab
<table>
    <th>Attribute Name</th>
    <th>Default Value</th>
    <th>Description</th>
    <tr>
        <td>app:cf_default_icon</td>
        <td>@drawable/ic_clap_hands_outline</td>
        <td>The default icon of the ClapFab button</td>
    </tr>
    <tr>
        <td>app:cf_filled_icon</td>
        <td>@drawable/ic_clap_hands_filled</td>
        <td>The filled icon after clapping of the ClapFab button</td>
    </tr>
    <tr>
        <td>app:cf_default_icon_color</td>
        <td>@color/colorClapIcon</td>
        <td>The color of default icon of the ClapFab button</td>
    </tr>
    <tr>
        <td>app:cf_filled_icon_color</td>
        <td>@color/colorClapIcon</td>
        <td>The filled color of icon after clapping of the ClapFab button</td>
    </tr>
    <tr>
        <td>app:cf_max_clap_count</td>
        <td>50</td>
        <td>The maximum count of clapping of the ClapFab button</td>
    </tr>
    <tr>
        <td>app:cf_count_circle_color</td>
        <td>@color/colorClapIcon</td>
        <td>The color of count's circle background</td>
    </tr>
    <tr>
        <td>app:cf_count_text_color</td>
        <td>@android:color/white</td>
        <td>The color of count's circle text</td>
    </tr>
    <tr>
        <td>app:cf_dots_1_color</td>
        <td>@color/dotsColor1</td>
        <td>The color of particle's dots 1</td>
    </tr>
    <tr>
        <td>app:cf_dots_2_color</td>
        <td>@color/dotsColor2</td>
        <td>The color of particle's dots 2</td>
    </tr>
</table>

## Clap Listener
```java
ClapFAB clapFAB = (ClapFAB) findViewById(R.id.clapFAB);
clapFAB.setClapListener(new ClapFAB.OnClapListener() {
            @Override
            public void onFabClapped(@NotNull ClapFAB clapFab, int count, boolean isMaxReached) {
                // count is the current count of the clapping
                // isMaxReached is true when button has already reached the maximum count 
                // and is not being clapped anymore. Otherwise its false
            }
        });
```

📃 Libraries Used
=============
* ViewAnimator [https://github.com/florent37/ViewAnimator](https://github.com/florent37/ViewAnimator)
* DotsView from Like Animation [https://github.com/jd-alexander/LikeButton](https://github.com/jd-alexander/LikeButton)

💰 Donations
=============

This project needs you! If you would like to support this project's further development, the creator of this project or the continuous maintenance of this project, feel free to donate. Your donation is highly appreciated (and I love food, coffee and beer). Thank you!

**PayPal**

* **[Donate $5](https://www.paypal.me/WajahatKarim/5)**: Thank's for creating this project, here's a tea (or some juice) for you!
* **[Donate $10](https://www.paypal.me/WajahatKarim/10)**: Wow, I am stunned. Let me take you to the movies!
* **[Donate $15](https://www.paypal.me/WajahatKarim/15)**: I really appreciate your work, let's grab some lunch!
* **[Donate $25](https://www.paypal.me/WajahatKarim/25)**: That's some awesome stuff you did right there, dinner is on me!
* **[Donate $50](https://www.paypal.me/WajahatKarim/50)**: I really really want to support this project, great job!
* **[Donate $100](https://www.paypal.me/WajahatKarim/100)**: You are the man! This project saved me hours (if not days) of struggle and hard work, simply awesome!
* **[Donate $2799](https://www.paypal.me/WajahatKarim/2799)**: Go buddy, buy Macbook Pro for yourself!

Of course, you can also choose what you want to donate, all donations are awesome!

👨 Developed By
============
```
Wajahat Karim
```
- Website (http://wajahatkarim.com)
- Twitter (http://twitter.com/wajahatkarim)
- Medium (http://www.medium.com/@wajahatkarim3)
- LinkedIn (http://www.linkedin.com/in/wajahatkarim)

# 👍 How to Contribute
1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request

# 📃 License

    Copyright 2018 Wajahat Karim

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
