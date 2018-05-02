# BeforeAndAfterView
Library for images comparison

![alt text](https://raw.githubusercontent.com/factoryhr/BeforeAndAfterView/master/BeforeAndAfterView.gif)

**Usage**

Add jitpack.io repository in your root build.gradle at the end of repositories:

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
     
Add the dependency:

	dependencies {
	          implementation 'com.github.factoryhr:BeforeAndAfterView:1.0'
	}

Add xmlns to your layout root element:

`xmlns:app="http://schemas.android.com/apk/res-auto"`

Declare the view in your layout:

      <com.d42gmail.cavar.beforeandafter.custom_view.BeforeAndAfterView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
        
Set images from drawable
XML:

     <com.d42gmail.cavar.beforeandafter.custom_view.BeforeAndAfterView
       android:id="@+id/beforeAndAfterView"
       android:layout_width="match_parent"
       android:layout_height="240dp"
       app:rightImageSrc="@drawable/image_before"
       app:leftImageSrc="@drawable/image_after"/>
   
Code: 

     beforeAndAfterView.loadImagesBySrc(R.drawable.image_after, R.drawable.image_before)

Load images from web URL
XML:

    <com.d42gmail.cavar.beforeandafter.custom_view.BeforeAndAfterView
       android:id="@+id/beforeAndAfterView"
       android:layout_width="match_parent"
       android:layout_height="240dp"
       app:rightImageUrl="http://zg.plavatvornica.com/zrinjevac/now_then/1.jpg"
       app:leftImageUrl="http://zg.plavatvornica.com/zrinjevac/now_then/2.jpg"/>
   
   Code: 
   
     beforeAndAfterView.loadImagesByUrl("http://zg.plavatvornica.com/zrinjevac/now_then/2.jpg", "http://zg.plavatvornica.com/zrinjevac/now_then/1.jpg")
     
**Supported attributes**


|  Attr         | Format        | Description    |
| ------------- | ------------- | ------------- |
| rightImageSrc  | reference  | Drawable displayed on right side  |
| leftImageSrc  | reference  | Drawable displayed on left side   |
| rightImageUrl  | string | Image from web displayed on right side|
| leftImageUrl  | string  | Image from web displayed on left side  |
| roundCorners  | boolean  | Round corners (white color)  |
| cornerMask  | reference  | Mask which is placed on top of views (like round corners mask) |
| progress  | integer  | Set thumb seek bar progress (0 - 100)|
| progressPaddingStart  | dimension | Used for thumb calibration  |
| progressPaddingEnd  | dimension  | Used for thumb calibration  |
| progressDrawable  | reference | Set thumb drawable  |
| placeHolderSrc  | reference  | Drawable which is shown in view before data loading  |

**Supported methods**

|  Method         | Description        | 
| ------------- | ------------- | 
| fun loadImagesByUrl(imageLeftUrl: String, imageRightUrl: String)  | Load two images from web  | 
| fun loadImagesByUrl(imageLeftrUrl: String, imageRightUrl: String, progress: Int)  | Load two images from web and set progress  | 
| fun loadImagesBySrc(imageLeftSrc: Int, imageRightSrc: Int)  | Load two drawables  | 
| fun loadImagesBySrc(imageLeftSrc: Int, imageRightSrc: Int, progress: Int)  | Load two drawables and set progress  | 
| fun setRoundCorners(roundCorners: Boolean)  | Set round corners (white)  | 
| fun setMask(drawable: Drawable)  | Mask which is placed on top of views (like round corners mask)  | 
| fun setProgressPadding(start: Int, top: Int, end: Int, bottom: Int)  | Used for thumb calibration  | 
| fun setProgressThumb(drawable: Drawable)  | Set thumb drawable  | 
| fun setPlaceHolder(drawable: Drawable)  | Drawable which is shown in view before data loading  | 
