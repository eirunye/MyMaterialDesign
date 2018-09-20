# MyMaterialDesign

# 简介
最近总结一下自己开发中常见Material Design的一些控件，**TextInputLayout** 的使用及解析常用的属性。
希望给大家带来帮助，同时通过学习来发现自己的不足，希望大家给予一些建议，哈哈。接下来开始撸代码。

>首先在build.gradle中添加依赖如下：
dependencies {
compile'com.android.support:appcompat-v7:25.0.1'
compile'com.android.support:design:25.0.1'
}

查看源码发现TextInputLayout布局封装以前最常用的编辑文本框EditText，这就让我们不禁想起EditText的友好画面了吧，不过现在这个TextInputLayout会让你更加喜欢上了这个优雅的控件，
在源码解释中：TextInputLayout的作用是：显示一个浮动的标签-->当提示隐藏由于用户输入文本。(英语比较渣渣)；
>因为TextInputLayout封装了EditText，所以在使用时还是不开EditText。在XML文件中声明如下：

         <android.support.design.widget.TextInputLayout
                android:id="@+id/login_til_username"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="20dp">
          <EditText
              android:id="@+id/login_et_username"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:hint="用户名"/>
        </android.support.design.widget.TextInputLayout>

>注意：这里的属性：android:hint="用户名"，当我点击EditText时，该hint的文本会悬浮在EditText的上方，这让我们的页面布局显示一个非常友好的设置。
接下来让我们来实践一下这个布局的常用的属性。
>默认显示横线的颜色是默认主题中 colorAccent
(1)**app:counterEnabled="true"字符计数是否可用**
(2)**app:counterMaxLength="24"设计计算最大的长度如:24**


        <android.support.design.widget.TextInputLayout
          android:id="@+id/til_username"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:hint="用户名"
          app:counterEnabled="true"
          app:counterMaxLength="24">

>(3)app:counterOverflowTextAppearance ="@style/TextInputOverflowText"计数超过最大长度时显示的文本样式风格(悬浮文本、底部横线均发生改变)

    <style name="TextInputOverflowText">
    <item name="android:textColor">#ddff23<item/>
    <item name="android:textSize">16sp<item/>
>(4)app:counterTextAppearance显示的计数的文本样式。
(5)app:hintTextAppearance =“@style/TextInputHint”设置hint显示的风格如文字大小、颜色等在“res/values/style”中设置一个风格：如下，

    <style name="TextInputHint">
    <item name="android:textColor">#CCDDFFBB<item/>
    <item name="android:textSize">16sp <item/>
    <item name="android:maxEms">1<item/>

>(6)app:errorEnabled ="true" 是否显示错误
(7)app:hintEnabled ="true" 是否设置浮动标签//默认为true，false表示没有hint文本
(8)app:hintAnimationEnabled="true" 是否设置悬浮动画默认为true
(9)app:passwordToggleDrawable="int"设置密码切换的图标在drawable获取图标
(10)app:passwordToggleEnabled="true"是否显示密码可见
(11)android:textCursorDrawable="@null"修改光标的样式
(12)app:passwordToggleTint="@color/colorPrimary" 改变 app:passwordToggleDrawable图标颜色
(13)android:textColorHint="#cccccc" hint中的字体颜色
(14)hintAnimationEnabled = "true" 是否激活动画

上面的属性大概了解了，那接下来就是编码咯。O(∩_∩)O哈哈~
>*3、上代码演示*

    <android.support.design.widget.TextInputLayout
        android:id="@+id/til_password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:hint="密码"
        android:paddingBottom="8dp"
        app:passwordToggleEnabled="true"
        app:passwordToggleTint="@color/colorPrimary"
      >
        android:id="@+id/et_login_password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textPassword"
      />
    </android.support.design.widget.TextInputLayout>

>4、处理错误信息显示

    button= (Button) findViewById(R.id.button);
    til_username= (TextInputLayout) findViewById(R.id.til_username);
    til_password= (TextInputLayout) findViewById(R.id.til_password);
    tie_login_username= (TextInputEditText) findViewById(R.id.tie_login_username);
    tie_login_password= (TextInputEditText) findViewById(R.id.tie_login_password);
    button.setOnClickListener(newView.OnClickListener() {
    @Override
    public voidonClick(View view) {
      getLoginInfo();//登录方法
    }
    });

>5、getLoginInfo()方法的代码：<简单的做一下判断而已>*(这里可以忽略)*

    private void getLoginInfo() {
    String username =tie_login_username.getText().toString().trim();
    String password =tie_login_password.getText().toString().toString();
    tie_login_username.addTextChangedListener(newTextWatcher() {
    @Override
    public voidbeforeTextChanged(CharSequence charSequence, inti, inti1, inti2) {
    }
    @Override
    public voidonTextChanged(CharSequence charSequence, inti, inti1, inti2) {
      til_username.setErrorEnabled(false);
    }
    @Override
    public voidafterTextChanged(Editable editable) {
    }
    });
      tie_login_password.addTextChangedListener(newTextWatcher() {
    @Override
    public voidbeforeTextChanged(CharSequence charSequence, inti, inti1, inti2) {
    }
    @Override
    public voidonTextChanged(CharSequence charSequence, inti, inti1, inti2) {
      til_password.setErrorEnabled(false);
    }
    @Override
    public voidafterTextChanged(Editable editable) {
    }
    });
    if(TextUtils.isEmpty(username)) {
      til_username.setError("用户名不能为空！");
      return;
    }
    if(TextUtils.isEmpty(password)) {
      til_password.setError("密码不能为空!");
      return;
    }
    if(password.length() >5&& username.length() >5) {
    Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }else if(password.length() <=5) {
      til_password.setError("密码错误");
    }else if(username.length() <=5) {
      til_username.setError("用户名错误");
    }
    }

>6、接下来讲解一下TextInputEditText。
TextInputEditText是EditText的子类，所以TextInputLayout同样也封装了该类，所以在开发的时候我们是可以任选择哪个来进行都是可以。
TextInputEditText继承了AppCompatibleEditText，而AppCompatibleEditText继承了EditText。
源码中所说的是使用该类是为了“使用这个类允许我们显示一个提示在输入法设置在“提取”模式”
允许一些背景设置方法。如：**setBackgroundResource(@DrawableResintresId)、setBackgroundDrawable(Drawable background)**等

    <android.support.design.widget.TextInputLayout
        android:id="@+id/til_password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="密码"
        app:counterEnabled="true"
        app:counterMaxLength="24"
        app:passwordToggleEnabled="true"
        app:hintAnimationEnabled="true"
        >
    <android.support.design.widget.TextInputEditText
        android:id="@+id/tie_login_password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textPassword"
      />
    </android.support.design.widget.TextInputLayout>

# 下载
>好了，本篇文章到这就完了，具体的页面GIF没有贴出，如果需要就去下载demo咯
[下载链接](https://github.com/iconye/myMaterialDesign)

# 总结

MD的TextInputLayout、TextInputEditText的功能总结一下，这也是我们的开发常用的。
