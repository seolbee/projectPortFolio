window.addEventListener("load", (e)=>{
    click_event();
    let section = document.querySelector("section");
    section.width = window.innerWidth;
    section.height = window.innerHeight;
})

function click_event(){
    let input = document.querySelectorAll("input");
    input.forEach(x=> x.isClick = false);
    input.forEach(x=> x.addEventListener("click", (e)=>{
        if(!x.isClick){
            x.style.border="2px solid #222";
            x.placeholder="";
            x.isClick = true;
        }
        
    }))

    document.addEventListener("click", ()=>{
        input.forEach(x=> {
            x.style.border="none";
            x.placeholder = `Enter ${x.className}`;
            x.isClick = false;
        })
    })
}

function login(){
    let btn = document.querySelector(".btn");
    btn.addEventListener("click", (e)=>{
        let id = document.querySelector("#id").value;
        let pass = document.querySelector("#password").value;
        if(id == "" || pass == ""){
            swal("빈 입력란이 존재합니다.", "","error");
            return;
        }
        let formData = new FormData();
        formData.append("id", id);
        formData.append("password", pass);
        fetch("/board/login", {
            method : "post",
            body: formData
        }).then(x=> x.json()).then(e=>{
            if(e.result){
                location.href="/";
            }
        });
    })
}

function register(){
    let btn =document.querySelector(".btn");
    btn.addEventListener("click", ()=>{
        let id = document.querySelector("#id").value;
        let pass = document.querySelector("#password").value;
        let pass_ok = document.querySelector("#password_again").value;
        let nicname = document.querySelector("#nicname").value;
        if(id == "" || pass == "" || pass_ok == "" | nicname == ""){
            swal("빈 입력란이 존재합니다.");
            return;
        }
        if(pass != pass_ok){
            swal("패스워드와 패스워드 확인이 다릅니다.");
            return;
        }
        let formData = new FormData();
        formData.append("id", id);
        formData.append("password", pass);
        formData.append("nicname", nicname);
        fetch("/board/register", {
            method : "post",
            body : formData
        }).then(e=> e.json()).then(x=> {
            alert(x.msg);
            if(x.result){
                location.href=x.link;
            } else if(x.link != ""){
                location.href=x.link;
            }
        });
    })
}

function slide(){
    let img = document.querySelectorAll(".img_box");
    let now = 0;
    let slide = false;
    img[now].classList.add("active");
    let section = document.querySelector("section");
    // setInterval(()=>{
    //     let next = now < 2 ? now + 1 : 0;
    //     img[now].classList.add("now");
    //     setTimeout(()=>img[now].classList.remove("now"), 1200);
    //     img[next].classList.add("next");
    //     setTimeout(()=>img[next].classList.remove("next"), 1200);
    //     img[now].classList.remove("active");
    //     img[next].classList.add("active");
    //     now = next;
    // }, 2000)
}

function logout(){
    let logout = document.querySelector(".logout");
    logout.addEventListener("click", (e)=>{
        e.preventDefault();
        fetch("/board/logout", {
            method : "get"
        }).then(e=> e.json()).then(x=> {
            location.href="/";
        })
    })
}

async function ranking(){
    let a = document.querySelector(".rank_btn");
    let btn = document.querySelector(".fas");
    let popup = document.querySelector(".popup");
    let page = 1;
    let data = await parser();
    let next_button = document.querySelector(".next_page");
    let prev_button = document.querySelector(".prev_page");
    a.addEventListener("click", (e)=>{
        page = 1;
        e.preventDefault();
        popup.style.display="block";
        loading(data.data.slice((page - 1) * 5, 5), page - 1);
    })
    btn.addEventListener("click", (e)=>popup.style.display="none");
    next_button.addEventListener("click",(e)=>{
        if(page >= Math.ceil(data.data.length / 5)) return false;
        page++;
        loading(data.data.slice((page -1) * 5, data.data.length), page - 1);
    })
    prev_button.addEventListener("click",()=>{
        if(page <= 1) return false;
        page--;
        loading(data.data.slice((page -1) * 5, 5), page - 1);
    })
}

function parser(){
    return new Promise((res)=>{
        let data = [];
        data = fetch("/ranking").then(e=> e.json());
        res(data);
    })
}

function loading(data, page){
    let popup = document.querySelector(".popup");
    popup.querySelector(".rank_content").innerHTML = "";
    for(let i = 0; i<data.length; i++){
        let div = document.createElement("div");
        div.classList.add("rank_template");
        div.innerHTML = `<p>${page * 5 + 1 + i}</p><p>${data[i].nicname}</p><p>${data[i].score}</p><p>${data[i].date}</p>`;
        popup.querySelector(".rank_content").appendChild(div);
    }
}