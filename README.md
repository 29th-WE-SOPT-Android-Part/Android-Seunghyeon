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
    * **그러게요 어떻게 개선할까요 ... ? 🥴**

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
