<h1> Android-Seunghyeon </h1>

![github_한승현_ver1-7](https://user-images.githubusercontent.com/70698151/135753583-cb6d8b51-421f-48e2-9284-0bb3b70bb6d7.png)

<details>
  <summary><b>1차 세미나 과제</b></summary>
<div markdown="1"> 
  <h4> 필수과제 </h4>

* **GIF**

  <img src="https://user-images.githubusercontent.com/81508084/136231628-7ac1b492-31db-4810-bce0-e6e518165e19.gif" width="30%" height="30%"/>


* **SignInActivity**

  * 로그인 버튼을 눌렀을 때, ID, PW가 모두 입력되어있을 시 HomeActivity로 이동하고 그렇지 않으면 Toast 출력

    ```kotlin
        private fun clickLogin() {
            if(!binding.etSigninId.text.isNullOrBlank() && !binding.etSigninPw.text.isNullOrBlank()) {
                Toast.makeText(this, "안녕하세요 ${binding.etSigninId.text}!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ID/PW를 확인해주세요!", Toast.LENGTH_SHORT).show()
            }
        }
    ```

    * ID, PW 입력 여부는 isNullOrBlank() 메서드를 활용하여 체크하였음
    * 각 조건문 분기마다 Toast 출력하였음

  * 비밀번호 EditText는 입력 내용이 가려져 있어야하고, 모든 EditText는 미리보기가 있어야 함

    ```xml
            <EditText
                android:id="@+id/et_signin_pw"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:hint="@string/signin_hint_pw"
                android:inputType="textPassword"
                android:maxLines="1"
                android:ellipsize="end"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/tv_signin_pw" />
    ```

    * 모든 EditText마다 hint 속성을 활용하여 미리보기를 추가하였고, 비밀번호 EditText의 경우 inputType을 textPassword로 설정하여 입력 내용을 가렸음

  * 회원가입 버튼을 누를 시 SignUpActivity로 이동

    ```kotlin
        private fun clickSignUp() {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    ```

* **SignUpActivity**

  * 회원가입 버튼을 눌렀을 때, 이름, ID, PW가 모두 입력되어있을 시 SignInActivity로 다시 돌아가고 그렇지 않으면 Toast 출력

    ```kotlin
        private fun clickSignUp() {
            if(!binding.etSignupName.text.isNullOrBlank() && !binding.etSignupId.text.isNullOrBlank() && !binding.etSignupPw.text.isNullOrBlank()) {
                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "이름/ID/PW를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    ```

    * 이름, ID, PW 입력 여부는 isNullOrBlank() 메서드를 활용하여 체크하였음
    * 각 조건문 분기마다 Toast 출력하였음
    * finish() 메서드를 활용하여 이전 스택의 Activity로 복귀하였음

  * 비밀번호 EditText는 입력 내용이 가려져 있어야하고, 모든 EditText는 미리보기가 있어야 함

    ```xml
            <EditText
                android:id="@+id/et_signup_pw"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:ellipsize="end"
                android:hint="@string/signin_hint_pw"
                android:inputType="textPassword"
                android:maxLines="1"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/tv_signup_pw" />
    ```

    * 모든 EditText마다 hint 속성을 활용하여 미리보기를 추가하였고, 비밀번호 EditText의 경우 inputType을 textPassword로 설정하여 입력 내용을 가렸음
</div>
</details>
<details>
  <summary><b>2차 세미나 과제</b></summary>
<div markdown="1"> 
  <h4> 필수과제 </h4>

* **GIF**

  <img src="https://user-images.githubusercontent.com/81508084/138326936-11d2cada-828f-458a-85a9-cc9adadbba19.gif" width="30%" height="30%"/>


* **자기소개 페이지를 만든 HomeActivity 하단에 FollowerRecyclerView, RepositoryRecyclerView 만들기(HomeActivity.kt)**

  * 각각의 RecyclerView를 담고있는 Fragment 2개 만들기

    * FollowerFragment, RepoFragment 생성

      ```xml
      <?xml version="1.0" encoding="utf-8"?>
      <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:tools="http://schemas.android.com/tools"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          tools:context=".FollowerFragment">
      
          <androidx.recyclerview.widget.RecyclerView
              android:id="@+id/rcv_follower"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              tools:itemCount="5"
              tools:listitem="@layout/item_follower" />
      
      </FrameLayout>
      ```

      ```xml
      <?xml version="1.0" encoding="utf-8"?>
      <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:tools="http://schemas.android.com/tools"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          tools:context=".RepoFragment">
      
          <androidx.recyclerview.widget.RecyclerView
              android:id="@+id/rcv_repo"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              tools:itemCount="5"
              tools:listitem="@layout/item_repo" />
      
      </FrameLayout>
      ```

  * 각각의 버튼을 눌렀을 때 알맞은 RecyclerView가 있는 Fragment로 전환하기

    * initTransaction() 을 구현하여 각각의 버튼을 눌렀을 때 알맞은 RecyclerView가 있는 Fragment로 전환

    * default로 보이는 Fragment는 FollowerFragment로 설정

      ```kotlin
          private fun initTransaction() {
              val followerFragment = FollowerFragment()
              val repoFragment = RepoFragment()
      
              supportFragmentManager.beginTransaction().add(R.id.frg_home_rcv, followerFragment).commit()
      
              binding.btnHomeRepo.setOnClickListener {
                  supportFragmentManager.beginTransaction().replace(R.id.frg_home_rcv, repoFragment)
                      .commit()
              }
              binding.btnHomeFollower.setOnClickListener {
                  supportFragmentManager.beginTransaction().replace(R.id.frg_home_rcv, followerFragment)
                      .commit()
              }
          }
      ```

  * 설명이 일정 길이를 넘어가면 xml의 ellipsize 속성을 활용

    ```xml
        <TextView
            android:id="@+id/tv_follower_info"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:ellipsize="end"
            android:maxLines="1"
            android:textSize="14sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="@id/tv_follower_name"
            app:layout_constraintTop_toBottomOf="@id/tv_follower_name"
            tools:text="info" />
    ```

    ```xml
        <TextView
            android:id="@+id/tv_repo_info"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_margin="5dp"
            android:ellipsize="end"
            android:maxLines="1"
            android:textSize="14sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/tv_repo_name"
            tools:text="info" />
    ```

* **둘 중 하나의 RecyclerView는 Grid Layout으로 만들기**

  * FollowerFragment의 RecyclerView를 Grid Layout으로 설정

    ```kotlin
    binding.rcvFollower.layoutManager = GridLayoutManager(context, 2)
    ```



#### 도전과제

* **GIF**

  <img src="https://user-images.githubusercontent.com/81508084/138404967-14a36317-3cd1-4020-a738-6a06d501b3ef.gif" width="30%" height="30%"/>

* **아이템 클릭 시 상세 설명을 보여주는 Activity로 이동하기(DetailActivity.kt)**

  * 이름과 사진은 화면 전환 시 넘겨줄 것

    * Adapter 생성자에 매개변수로 itemClick 선언

      ```kotlin
      class FollowerAdapter(val itemClick: (FollowerData) -> Unit) :
          RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>()
      ```

    * Fragment에서 Adapter 객체 생성 시 itemClickListener 구현

      ```kotlin
          private val adapter by lazy {
              FollowerAdapter() {
                  val intent = Intent(context, DetailActivity::class.java)
                  intent.putExtra("profile", it.image)
                  intent.putExtra("name", it.name)
                  intent.putExtra("detailInfo", it.detailInfo)
                  startActivity(intent)
              }
          }
      ```

    * DetailActivity에서 getExtra 사용해 구현

      ```kotlin
              val profile = intent.getIntExtra("profile", 0)
              val name = intent.getStringExtra("name")
              val detailInfo = intent.getStringExtra("detailInfo")
      
              binding.imgDetailProfile.setImageResource(profile)
              binding.tvDetailName.text = name
              binding.tvDetailInfo.text = detailInfo
      ```

* **ItemDecoration 활용해서 리스트 간 간격과 구분선 주기**

  * ItemDecoration을 활용해서 구분선 넣기

    * ItemDecoration을 상속받은 MyDecoration 클래스 구현

    * onDrawOver 메서드를 오버라이드하여 구분선 넣기

      ```kotlin
          override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
              val paint = Paint()
              paint.color = color
      
              val left = parent.paddingStart + padding
              val right = parent.width - parent.paddingEnd - padding
      
              for (i in 0 until parent.childCount) {
                  val child = parent.getChildAt(i)
                  val params = child.layoutParams as RecyclerView.LayoutParams
      
                  val top = (child.bottom + params.bottomMargin).toFloat()
                  val bottom = top + height
      
                  c.drawRect(left, top, right, bottom, paint)
              }
          }
      ```

    * getItemOffsets 메서드를 오버라이드하여 아이템 간 간격(margin) 주기

      ```kotlin
          override fun getItemOffsets(
              outRect: Rect,
              view: View,
              parent: RecyclerView,
              state: RecyclerView.State
          ) {
              super.getItemOffsets(outRect, view, parent, state)
              outRect.bottom += padding.toInt()
              outRect.top += padding.toInt()
              outRect.left += padding.toInt()
              outRect.right += padding.toInt()
          }
      ```

* **RecyclerView Item 이동 삭제 구현**

  * ItemTouchHelper.SimpleCallback 사용

    ```kotlin
    val itemTouchHelperCallback =
                object :
                    ItemTouchHelper.SimpleCallback(
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    ) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        val fromPos = viewHolder.adapterPosition
                        val toPos = target.adapterPosition
                        val temp = adapter.itemList[fromPos]
                        if(fromPos < toPos) {
                            for(i in fromPos until toPos) {
                                adapter.itemList[i] = adapter.itemList[i+1]
                            }
                            adapter.itemList[toPos] = temp
                        } else if(fromPos > toPos) {
                            for(i in toPos+1..fromPos) {
                                adapter.itemList[i] = adapter.itemList[i-1]
                            }
                            adapter.itemList[toPos] = temp
                        }
                        adapter.notifyItemMoved(fromPos, toPos)
    
                        return true
                    }
    
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val delPos = viewHolder.adapterPosition
                        adapter.itemList.removeAt(delPos)
                        adapter.notifyItemRemoved(delPos)
                    }
                }
            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
            itemTouchHelper.attachToRecyclerView(binding.rcvFollower)
    ```

    * Grid Layout인 rcvFollower에서는 dragDirs를 상하좌우, swipeDirs를 좌우로 설정했고 Linear Layout인 rcvRepo에서는 dragDirs를 상하, swipeDirs를 좌우로 설정함

  

#### 심화과제

* **보일러 플레이트 코드 개선**
  * 보일러 플레이트 코드
    * 최소한의 변경으로 여러 곳에서 재사용되며, 반복적으로 비슷한 형태를 띄는 코드
    * BaseActivity, BaseFragment를 사용

* **보다 효율적으로 RecyclerView의 아이템을 갱신하기**
  * notifyDataSetChanged
    * Adapter가 DataSet 전부를 갱신하도록 하는 메서드
    * 리스트의 크기와 아이템 둘 다 변경되는 경우에 사용
    * 문제점: DataSet의 크기가 작은 경우에는 별로 티나지 않지만, DataSet의 크기가 커질 경우 일부만 갱신하면 될 일을 굳이 전체를 갱신한다면? >> 비효율적
  * notifyItemChanged(position: Int)
    * 특정 position의 아이템만 변경된 경우 사용
  * notifyItemRangeChanged(positionStart: Int, itemCount: Int)
    * 특정 positionStart부터 itemCount 개수만큼 아이템이 변경된 경우 사용
  * notifyItemInserted(position: Int)
    * 특정 position에 아이템이 삽입된 경우 사용
  * notifyItemRangeInserted(positionStart:Int, itemCount: Int)
    * 특정 positionStart부터 itemCount 개수만큼 아이템이 삽입된 경우 사용
  * notifyItemRemoved(position: Int)
    * 특정 position에서 아이템이 삭제된 경우 사용
    * **ItemTouchHelper.SimpleCallback의 onSwipe에서 아이템 삭제했을 때 사용함**
  * notifyItemRangeRemoved(positionStart: Int, itemCount: Int)
    * 특정 positionStart부터 itemCount 개수만큼 아이템이 삭제된 경우 사용
  * notifyItemMoved(fromPosition: Int, toPosition: Int)
    * fromPosition에 있던 아이템이 toPosition으로 이동한 경우 사용
    * **ItemTouchHelper.SimpleCallback의 onMove에서 아이템 이동했을 때 사용함**
</div>
</details>
  
<details>
<summary><b>3차 세미나 과제</b></summary>
<div markdown="1"> 
  <h4> 필수과제 </h4>

* **GIF**
  
  <img src="https://user-images.githubusercontent.com/81508084/141163971-5377cac6-6d6c-4652-9884-693717d27689.gif" width="30%" height="30%"/>


* **과제에 디자인 적용하기**


  * **로그인 화면 / 회원가입 화면**


    * EditText에 selector 활용하기(focus 되었을 때, 안 되었을 때)


      * selector_textbox

        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <selector xmlns:android="http://schemas.android.com/apk/res/android">
            <item android:drawable="@drawable/shape_textbox_not_empty" android:state_focused="true" />
            <item android:drawable="@drawable/shape_textbox_empty" android:state_focused="false" />
        </selector>
        ```

    * 간단한 도형들은 ShapeDrawable로 직접 만들기


      * shape_textbox_not_empty.xml

        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <selector xmlns:android="http://schemas.android.com/apk/res/android">
            <item android:drawable="@drawable/shape_textbox_not_empty" android:state_focused="true" />
            <item android:drawable="@drawable/shape_textbox_empty" android:state_focused="false" />
        </selector>
        ```

      * shape_textbox_empty.xml

        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <shape xmlns:android="http://schemas.android.com/apk/res/android"
            android:shape="rectangle">
            <solid android:color="@color/textbox_empty_body" />
            <stroke
                android:width="1dp"
                android:color="@color/textbox_empty_border" />
            <padding
                android:bottom="13dp"
                android:left="16dp"
                android:right="16dp"
                android:top="13dp" />
            <corners android:radius="5dp" />
        </shape>
        ```

      * shape_button_sign.xml

        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <shape xmlns:android="http://schemas.android.com/apk/res/android"
            android:shape="rectangle">
            <solid android:color="@color/medium_pink" />
            <corners android:radius="5dp" />
            <padding
                android:bottom="12dp"
                android:top="12dp" />
        </shape>
        ```

  * **ProfileFragment**


    * Button에 selector 활용하기(선택되었을 때, 안 되었을 때)


      * selector_button.xml

        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <selector xmlns:android="http://schemas.android.com/apk/res/android">
            <item android:state_selected="true" android:drawable="@drawable/shape_selected_button"/>
            <item android:state_selected="false" android:drawable="@drawable/shape_unselected_button"/>
        </selector>
        ```

      * shape_selected_button.xml

        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <shape xmlns:android="http://schemas.android.com/apk/res/android"
            android:shape="rectangle">
            <solid android:color="@color/button_selected" />
            <corners android:radius="5dp" />
        </shape>
        ```

      * shape_unselected_button.xml

        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <shape xmlns:android="http://schemas.android.com/apk/res/android"
            android:shape="rectangle">
            <solid android:color="@color/textbox_empty_body" />
            <corners android:radius="5dp" />
        </shape>
        ```

    * 이미지의 경우 Glide의 CircleCrop 기능 활용해서 넣어주기


      * ProfileFragment.kr

        ```kotlin
            private fun initProfilePicture() {
                Glide.with(requireContext())
                    .load("https://avatars.githubusercontent.com/u/81508084?v=4")
                    .circleCrop()
                    .into(binding.imgProfilePicture)
            }
        ```

    * 하단에 BottomNavigation 넣어주기


      * MainActivity.kt

        ```kotlin
            private fun initViewPagerAdapter() {
                val fragmentList = listOf(profileFragment, homeFragment, cameraFragment)
                viewPagerAdapter = MainViewPagerAdapter(this)
                viewPagerAdapter.fragments.addAll(fragmentList)
                binding.vpMain.adapter = viewPagerAdapter
            }
        
            private fun initBottomNavigation() {
                binding.vpMain.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        binding.bnvMain.menu.getItem(position).isChecked = true
                    }
                })
        
                binding.bnvMain.setOnItemSelectedListener {
                    binding.vpMain.currentItem = when(it.itemId) {
                        R.id.menu_profile -> PROFILE_FRAGMENT
                        R.id.menu_home -> HOME_FRAGMENT
                        else -> CAMERA_FRAGMENT
                    }
                    return@setOnItemSelectedListener true
                }
            }
        
            private companion object {
                const val PROFILE_FRAGMENT = 0
                const val HOME_FRAGMENT = 1
                const val CAMERA_FRAGMENT = 2
            }
        ```

  * **HomeFragment**


    * 3차 세미나에서 배웠던 TabLayout + ViewPager2 넣어주기


      * HomeFragment.kt

        ```kotlin
            private fun initViewPager() {
                val fragmentList = listOf(homeFollowingFragment, homeFollowerFragment)
                viewPagerAdapter = HomeViewPagerAdapter(this)
                viewPagerAdapter.fragments.addAll(fragmentList)
                binding.vpHome.adapter = viewPagerAdapter
            }
        
            private fun initTabLayout() {
                val tabLabel = listOf("팔로잉", "팔로워")
        
                TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
                    tab.text = tabLabel[position]
                }.attach()
            }
        ```

  <h4> 도전과제 </h4>

* **ViewPager2 중첩 스크롤 문제 해결하기**

  * NestedScrollableHost로 내부 ViewPager2를 wrapping하여 처리함

    * NestedScrollableHost.kt

      ```kotlin
      class NestedScrollableHost : FrameLayout {
          constructor(context: Context) : super(context)
          constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
      
          private var touchSlop = 0
          private var initialX = 0f
          private var initialY = 0f
          private val parentViewPager: ViewPager2?
              get() {
                  var v: View? = parent as? View
                  while (v != null && v !is ViewPager2) {
                      v = v.parent as? View
                  }
                  return v as? ViewPager2
              }
      
          private val child: View? get() = if (childCount > 0) getChildAt(0) else null
      
          init {
              touchSlop = ViewConfiguration.get(context).scaledTouchSlop
          }
      
          private fun canChildScroll(orientation: Int, delta: Float): Boolean {
              val direction = -delta.sign.toInt()
              return when (orientation) {
                  0 -> child?.canScrollHorizontally(direction) ?: false
                  1 -> child?.canScrollVertically(direction) ?: false
                  else -> throw IllegalArgumentException()
              }
          }
      
          override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
              handleInterceptTouchEvent(e)
              return super.onInterceptTouchEvent(e)
          }
      
          private fun handleInterceptTouchEvent(e: MotionEvent) {
              val orientation = parentViewPager?.orientation ?: return
      
              // Early return if child can't scroll in same direction as parent
              if (!canChildScroll(orientation, -1f) && !canChildScroll(orientation, 1f)) {
                  return
              }
      
              if (e.action == MotionEvent.ACTION_DOWN) {
                  initialX = e.x
                  initialY = e.y
                  parent.requestDisallowInterceptTouchEvent(true)
              } else if (e.action == MotionEvent.ACTION_MOVE) {
                  val dx = e.x - initialX
                  val dy = e.y - initialY
                  val isVpHorizontal = orientation == ORIENTATION_HORIZONTAL
      
                  // assuming ViewPager2 touch-slop is 2x touch-slop of child
                  val scaledDx = dx.absoluteValue * if (isVpHorizontal) .5f else 1f
                  val scaledDy = dy.absoluteValue * if (isVpHorizontal) 1f else .5f
      
                  if (scaledDx > touchSlop || scaledDy > touchSlop) {
                      if (isVpHorizontal == (scaledDy > scaledDx)) {
                          // Gesture is perpendicular, allow all parents to intercept
                          parent.requestDisallowInterceptTouchEvent(false)
                      } else {
                          // Gesture is parallel, query child if movement in that direction is possible
                          if (canChildScroll(orientation, if (isVpHorizontal) dx else dy)) {
                              // Child can scroll, disallow all parents to intercept
                              parent.requestDisallowInterceptTouchEvent(true)
                          } else {
                              // Child cannot scroll, allow all parents to intercept
                              parent.requestDisallowInterceptTouchEvent(false)
                          }
                      }
                  }
              }
          }
      }
      ```

    * fragment_home.xml

      ```xml
          <co.kr.soptandroidseminar.home.NestedScrollableHost
              android:id="@+id/nsh_home"
              android:layout_width="match_parent"
              android:layout_height="0dp"
              app:layout_constraintBottom_toBottomOf="parent"
              app:layout_constraintEnd_toEndOf="parent"
              app:layout_constraintStart_toStartOf="parent"
              app:layout_constraintTop_toBottomOf="@id/tab_home">
      
              <androidx.viewpager2.widget.ViewPager2
                  android:id="@+id/vp_home"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent" />
      
          </co.kr.soptandroidseminar.home.NestedScrollableHost>
      ```

* **리스트에 각기 다른 이미지 넣기**

  * RecyclerView의 data class에 url을 저장할 변수 추가

    * FollowerData.kt

      ```kotlin
      data class FollowerData(
          val image: String,
          val name: String,
          val info: String,
          val detailInfo: String,
      )
      ```

    * FollowerAdapter.kt

      ```kotlin
      fun onBind(data: FollowerData) {
                  Glide.with(binding.imgFollowerProfile.context)
                      .load(data.image)
                      .circleCrop()
                      .into(binding.imgFollowerProfile)
      
                  binding.tvFollowerName.text = data.name
                  binding.tvFollowerInfo.text = data.info
      
                  binding.root.setOnClickListener {
                      itemClick(data)
                  }
              }
      ```

  <h4> 심화과제 </h4>

* 갤러리에서 받아온 이미지(uri)를 Glide로 화면에 띄워보기

  * 인텐트를 이용해 갤러리에 접근

    ```kotlin
        private fun openGallery() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            activityLauncher.launch(intent)
        }
    ```

  * 사진데이터를 uri 형식으로 받아온 이후 Glide로 이미지뷰에 띄우기

    ```kotlin
        private val activityLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if(it.resultCode == RESULT_OK && it.data != null) {
                    val imageUri = it.data?.data
                    runCatching {
                        Glide.with(this)
                            .load(imageUri)
                            .into(binding.imgCamera)
                    }.onFailure {
                        makeToast("사진 첨부 실패")
                    }
                } else if(it.resultCode == RESULT_CANCELED) {
                    makeToast("사진 선택 취소")
                }
            }
    ```
</div>
</details>

<details>
<summary><b>4차 세미나 과제</b></summary>
<div markdown="1"> 
    <h4> 필수과제 </h4>

* **GIF**

  <img src="https://user-images.githubusercontent.com/81508084/141174588-b604a63e-25cd-43de-91e8-04177bdbcf48.gif" width="30%" height="30%"/>


* **로그인/회원가입 API 연동**


  * SeminarService.kt

    ```kotlin
    interface SeminarService {
        @POST("user/signup")
        fun postSignUp(
            @Body body: RequestSignUpData
        ) : Call<ResponseSignUpData>
    
        @POST("user/login")
        fun postSingIn(
            @Body body: RequestSignInData
        ) : Call<ResponseSignInData>
    }
    ```

  * RequestSignInData.kt

    ```kotlin
    data class RequestSignInData(
        val email: String,
        val password: String,
    )
    ```

  * ResponseSignInData.kt

    ```kotlin
    data class ResponseSignInData(
        val status: Int,
        val success: Boolean,
        val message: String,
        val data: Data
    )  {
        data class Data(
            val id: Int,
            val name: String,
            val email: String,
        )
    }
    ```

  * RequestSignUpData.kt

    ```kotlin
    data class RequestSignUpData(
        val email: String,
        val name: String,
        val password: String,
    )
    ```

  * ResponseSignUpData.kt

    ```kotlin
    data class ResponseSignUpData(
        val status: Int,
        val success: Boolean,
        val message: String,
        val data: Data
    ) {
        data class Data(
            val id: Int,
            val name: String,
            val password: String,
            val email: String,
        )
    }
    ```


  * SignUpActivity.kt

    ```kotlin
        private fun clickSignUp() {
            if(!binding.etSignupName.text.isNullOrBlank() && !binding.etSignupId.text.isNullOrBlank() && !binding.etSignupPw.text.isNullOrBlank()) {
                val requestSignUpData = RequestSignUpData(
                    binding.etSignupId.text.toString(),
                    binding.etSignupName.text.toString(),
                    binding.etSignupPw.text.toString()
                )
    
                val call: Call<ResponseSignUpData> = ApiService.seminarService.postSignUp(requestSignUpData)
    
                call.enqueue(object: Callback<ResponseSignUpData> {
                    override fun onResponse(
                        call: Call<ResponseSignUpData>,
                        response: Response<ResponseSignUpData>
                    ) {
                        if(response.isSuccessful) {
                            val data = response.body()
                            Toast.makeText(this@SignUpActivity, data?.message, Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d("server connect : SignUp", "error")
                            Log.d("server connect : SignUp", "$response.errorBody()")
                            Log.d("server connect : SignUp", response.message())
                            Log.d("server connect : SignUp", "${response.code()}")
                            Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
    
                    override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                })
    
                finish()
            } else {
                Toast.makeText(this, "이름/ID/PW를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    ```

  * SignInActivity.kt

    ```kotlin
        private fun clickLogin() {
            if(!binding.etSigninId.text.isNullOrBlank() && !binding.etSigninPw.text.isNullOrBlank()) {
                val requestSignInData = RequestSignInData(
                    binding.etSigninId.text.toString(),
                    binding.etSigninPw.text.toString()
                )
    
                val call: Call<ResponseSignInData> = ApiService.seminarService.postSingIn(requestSignInData)
    
                call.enqueue(object: Callback<ResponseSignInData> {
                    override fun onResponse(
                        call: Call<ResponseSignInData>,
                        response: Response<ResponseSignInData>
                    ) {
                        if(response.isSuccessful) {
                            val data = response.body()?.data
                            Toast.makeText(this@SignInActivity, "안녕하세요 ${data?.name}!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignInActivity, MainActivity::class.java)
                            intent.putExtra("name", data?.name)
                            intent.putExtra("email", data?.email)
                            startActivity(intent)
                        } else {
                            Log.d("server connect : SignIn", "error")
                            Log.d("server connect : SignIn", "$response.errorBody()")
                            Log.d("server connect : SignIn", response.message())
                            Log.d("server connect : SignIn", "${response.code()}")
                            Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignInActivity, MainActivity::class.java)
                            intent.putExtra("name", "hansh0101")
                            intent.putExtra("email", binding.etSigninId.text.toString())
                            startActivity(intent)
                        }
                    }
    
                    override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
                        Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "ID/PW를 확인해주세요!", Toast.LENGTH_SHORT).show()
            }
        }
    ```

  <h4> 도전과제 </h4>

* **Github API 연동해서 리스트로 띄우기**

  * 유저 프로필, 팔로워 리스트, 레포지토리 리스트 불러오기

    * GithubService.kt

      ```kotlin
      interface GithubService {
          @GET("/users/{username}")
          fun getUserInfo(
              @Path("username") username: String
          ): Call<ResponseUserInfoData>
      
          @GET("/users/{username}/followers")
          fun getFollowerList(
              @Path("username") username: String
          ): Call<List<ResponseFollowerData>>
      
          @GET("/users/{username}/repos")
          fun getRepoList(
              @Path("username") username: String
          ): Call<List<ResponseRepoData>>
      }
      ```

    * ResponseUserInfoData.kt

      ```kotlin
      data class ResponseUserInfoData(
          val avatar_url: String,
          val bio: String?,
          val login: String,
          val name: String,
      )
      ```

    * ResponseFollowerData.kt

      ```kotlin
      data class ResponseFollowerData(
          val login: String,
      )
      ```

    * ResponseRepoData.kt

      ```kotlin
      data class ResponseRepoData(
          val name: String,
          val description: String,
      )
      ```

    * ProfileFragment.kt

      ```kotlin
          private fun getServerData() {
              val call: Call<ResponseUserInfoData> = ApiService.githubService.getUserInfo(username)
      
              call.enqueue(object : Callback<ResponseUserInfoData> {
                  override fun onResponse(
                      call: Call<ResponseUserInfoData>,
                      response: Response<ResponseUserInfoData>
                  ) {
                      if (response.isSuccessful) {
                          val data = response.body()
                          data?.avatar_url?.let { initProfilePicture(it) }
                          binding.tvProfileName.text = data?.name
                          binding.tvProfileId.text = data?.login
                          data?.bio?.let { binding.tvProfileIntro.text = it }
                          initTransaction()
                      } else {
                          Log.d("server connect : Profile Fragment", "error")
                          Log.d("server connect : Profile Fragment", "$response.errorBody()")
                          Log.d("server connect : Profile Fragment", response.message())
                          Log.d("server connect : Profile Fragment", "${response.code()}")
                      }
                  }
      
                  override fun onFailure(call: Call<ResponseUserInfoData>, t: Throwable) {
                      Log.d("server connect : Profile Fragment", "error: ${t.message}")
                  }
              })
          }
      ```

    * FollowerFragment.kt

      ```kotlin
          private fun getFollowerList() {
              val call: Call<List<ResponseFollowerData>> =
                  ApiService.githubService.getFollowerList(username)
      
              call.enqueue(object : Callback<List<ResponseFollowerData>> {
                  override fun onResponse(
                      call: Call<List<ResponseFollowerData>>,
                      response: Response<List<ResponseFollowerData>>
                  ) {
                      if (response.isSuccessful) {
                          val data = response.body()
                          if (data != null) {
                              getFollowerInfo(data)
                          }
                      } else {
                          Log.d("server connect : Follower Fragment", "error")
                          Log.d("server connect : Follower Fragment", "$response.errorBody()")
                          Log.d("server connect : Follower Fragment", response.message())
                          Log.d("server connect : Follower Fragment", "${response.code()}")
                      }
                  }
      
                  override fun onFailure(call: Call<List<ResponseFollowerData>>, t: Throwable) {
                      Log.d("server connect : Follower Fragment", "error: ${t.message}")
                  }
              })
          }
      ```

      ```kotlin
          private fun getFollowerInfo(list: List<ResponseFollowerData>) {
              list.forEach {
                  val call: Call<ResponseUserInfoData> = ApiService.githubService.getUserInfo(it.login)
      
                  call.enqueue(object : Callback<ResponseUserInfoData> {
                      override fun onResponse(
                          call: Call<ResponseUserInfoData>,
                          response: Response<ResponseUserInfoData>
                      ) {
                          if (response.isSuccessful) {
                              val data = response.body()
                              adapter.itemList.add(
                                  FollowerData(
                                      data?.avatar_url,
                                      data?.login,
                                      data?.name,
                                      data?.bio
                                  )
                              )
                              adapter.notifyItemInserted(adapter.itemList.size - 1)
                              Log.d("server connect : Follower Fragment", "success")
                              Log.d("server connect : Follower Fragment", it.login)
                          } else {
                              Log.d("server connect : Follower Fragment", "error")
                              Log.d("server connect : Follower Fragment", "$response.errorBody()")
                              Log.d("server connect : Follower Fragment", response.message())
                              Log.d("server connect : Follower Fragment", "${response.code()}")
                          }
                      }
      
                      override fun onFailure(call: Call<ResponseUserInfoData>, t: Throwable) {
                          Log.d("server connect: FollowerFragment", "error: ${t.message}")
                      }
                  })
              }
              initRecyclerView()
          }
      ```

    * RepoFragment.kt

      ```kotlin
          private fun getRepoList() {
              val call: Call<List<ResponseRepoData>> = ApiService.githubService.getRepoList(username)
      
              call.enqueue(object : Callback<List<ResponseRepoData>> {
                  override fun onResponse(
                      call: Call<List<ResponseRepoData>>,
                      response: Response<List<ResponseRepoData>>
                  ) {
                      if (response.isSuccessful) {
                          val data = response.body()
                          data?.forEach {
                              adapter.itemList.add(
                                  RepoData(
                                      it.name,
                                      it.description
                                  )
                              )
                              adapter.notifyItemInserted(adapter.itemList.size - 1)
                          }
                      } else {
                          Log.d("server connect : Repo Fragment", "error")
                          Log.d("server connect : Repo Fragment", "$response.errorBody()")
                          Log.d("server connect : Repo Fragment", response.message())
                          Log.d("server connect : Repo Fragment", "${response.code()}")
                      }
                  }
      
                  override fun onFailure(call: Call<List<ResponseRepoData>>, t: Throwable) {
                      Log.d("server connect : Repo Fragment", "error: ${t.message}")
                  }
              })
          }
      ```

* **OkHttp 활용해보기**

  * ApiService.kt

    ```kotlin
        private val soptRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_SOPT)
            .client(provideSoptOkHttpClient(SoptInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            
        private fun provideSoptOkHttpClient(
            interceptor: SoptInterceptor
        ): OkHttpClient =
            OkHttpClient.Builder()
                .run {
                    addInterceptor(interceptor)
                    build()
                }
    
        class SoptInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
                val newRequest =
                    request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build()
    
                proceed(newRequest)
            }
        }
    ```
</div>
</details>
