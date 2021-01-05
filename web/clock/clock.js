window.onload = function(){
	/*시계 만들기*/

	let ch=document.querySelector(".ch");
	let cm=document.querySelector(".cm");
	let cs=document.querySelector(".cs");
	let LED=document.querySelector("#led");
	let cd=document.querySelector("#cd");
	let mo=document.querySelector("#mo");
	let year=document.querySelector("#year");

	function format(value){
		let temp = "0" + value;
		return temp.substr(temp.length - 2, 2);
	}

	function task(){

		let d = new Date();

		year.innerHTML = d.getFullYear();
		mo.innerHTML = format(d.getMonth()+1);
		cd.innerHTML = format(d.getDate());
		ch.innerHTML = format(d.getHours());
		cm.innerHTML = format(d.getMinutes());
		cs.innerHTML = format(d.getSeconds());
	}

	let run = true;
	let timer = setInterval(task, 1000);

	let btn = document.querySelector("#btn");
	
	btn.addEventListener("click", function(e){
		if(run){
			LED.style.background="red";
			LED.style.boxShadow="0px 0px 10px 5px red";
			btn.style.color="black";
			clearInterval(timer);
			e.target.innerHTML = "시간 시작";
		} else{
			LED.style.background="green";
			LED.style.boxShadow="0px 0px 10px 5px green";
			btn.style.color="#eee";
			timer=setInterval(task, 1000);
			e.target.innerHTML = "시간 정지";
		}

		run = !run;
	});

	let btnStart = document.querySelector("#btnStart");

	btnStart.addEventListener("click", function(e){
		let sh = document.querySelector("#sh").innerHTML;
		let sm = document.querySelector("#sm").innerHTML;
		let ss = document.querySelector("#ss").innerHTML;
		let time = 0;

		if(isNaN(sh)||isNaN(sm)||isNaN(ss)){
			alert("숫자만 입력하시오");
			document.querySelector("#sh").innerHTML = format(0);
			document.querySelector("#sm").innerHTML = format(0);
			document.querySelector("#ss").innerHTML = format(0);
			return;
		}

		btn.disabled = true;
		time=sh * 3600 + sm*60 + ss;
		let timerId=setInterval(function(){
			time --;
			if(time < 0){
				time = 0;
				clearInterval(timerId);
				alert("종료");
			}
			btn.disabled = false;
			timeset(time);
		}, 1000)
	});

	function timeset (v){
		let sh = document.querySelector("#sh");
		let sm = document.querySelector("#sm");
		let ss = document.querySelector("#ss");

		sh.innerHTML = format(Math.floor(v/3600));
		sm.innerHTML = format(Math.floor(v%3600/60));
		ss.innerHTML = format(v%60);
	}

	let btn2=document.querySelector("#btn2");
	btn2.addEventListener("click", function(e){

		let h=document.querySelector("#h").innerHTML;
		let m=document.querySelector("#m").innerHTML;
		let s=document.querySelector("#s").innerHTML;

		if(isNaN(h) || isNaN(m) || isNaN(s)){
			alert("문자없이 입력하시오");
			document.querySelector("#h").innerHTML = format(0);
			document.querySelector("#m").innerHTML = format(0);
			document.querySelector("#s").innerHTML = format(0);
			return;
		}

		let th=document.querySelector("#th");
		let tm=document.querySelector("#tm");
		let ts=document.querySelector("#ts");

		setInterval(function(e){
			let a = new Date();

			th.innerHTML=format(a.getHours());
			tm.innerHTML=format(a.getMinutes());
			ts.innerHTML=format(a.getSeconds());

			if(h==th.innerHTML && m==tm.innerHTML && s==ts.innerHTML){
				alert("종료");
			}
		}, 1000);

	});
} 