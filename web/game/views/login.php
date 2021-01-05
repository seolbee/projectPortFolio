<section id="login">
        <div class="input_contain">
            <p>로그인</p>
            <div class="input_box">
                <input type="text" id="id" name="id" placeholder="Enter id" class="id">
            </div>
            <div class="input_box">
                <input type="password" id="password" name="password" placeholder="Enter password" class="password">
            </div>
            <button type="button" class="btn">Login</button>
            <span>회원가입이 되지 않으셨다면? <a href="/register">회원가입하러 가기</a></span>
        </div>
    </section>
    <script src="js/Web.js"></script>
    <script>
        window.addEventListener("load", ()=>{
            login();
        })
    </script>