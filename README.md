# ShareImagePopu
高仿币世界的分享图片功能

效果图：
![image](https://github.com/zhoudodo/ShareImagePopu/blob/master/GIF.gif) 

如何使用该功能
1、添加依赖
 ```javascript
	dependencies {
	        implementation 'com.github.zhoudodo:ShareImagePopu:v1.0'
	}
  ```
  
 2、如何需要延时可以使用如下延时方法 如果自定义图片内没有2次网络操作可以直接显示
 ```javascript
public class MainActivity extends FragmentActivity {
    ShareImagePopu mShareImagePopu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mShareImagePopu){
                    mShareImagePopu = new ShareImagePopu(MainActivity.this);
                }
                mShareImagePopu.delayMillionsShowBottom(200);
            }
        });
    }
}
```
