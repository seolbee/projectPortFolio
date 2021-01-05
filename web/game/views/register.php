<section id="login">
        <div class="input_contain">
            <p>회원가입</p>
            <div class="input_box">
                <input type="text" id="id" name="id" placeholder="Enter id" class="id">
            </div>
            <div class="input_box">
                <input type="password" id="password" name="password" placeholder="Enter password" class="password">
            </div>
            <div class="input_box">
                <input type="password" id="password_again" name="password" placeholder="Enter password again" class="password again">
            </div>
            <div class="input_box">
                <input type="text" id="nicname" name="nicname" placeholder="Enter nicname" class="nicname">
            </div>
            <button type="button" class="btn">Register</button>
        </div>
    </section>
    <script src="js/Web.js"></script>
    <script>
        window.addEventListener("load", (e)=>{
            register();
        })
    </script>