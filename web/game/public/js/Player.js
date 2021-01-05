class Player extends Game{

    constructor(){
        super();
        this.currentFireTerm = 0;
        this.fireTerm = 0.2;
        this.bulletSpeed = 400;
        this.bulletLength = 3;
        this.bulletDemage = 1;
        this.shieldTime = false;
    }

    reset(x, y, w, h, s, v , img, hp, heart, bl, shield){
        super.reset(x, y, w, h, s, v, img, hp);
        this.heart = heart;
        this.bulletLength = bl;
        this.shield = 1;
        this.shiedl_img = shield;
        this.bulletDemage = 1;
        this.bulletSpeed = 400;
        this.fireTerm = 0.2;
        this.shieldTime = false;
    }
    init(){
        document.addEventListener("keydown", e=>{
            if(e.code == "ArrowUp") this.keyArr[0] = true;
            if(e.code == "ArrowDown") this.keyArr[1] = true;
            if(e.code == "ArrowLeft") this.keyArr[2] = true;
            if(e.code == "ArrowRight") this.keyArr[3] = true;
            if(e.code == "KeyZ") this.keyArr[4] = true;
            if(e.code == "KeyC") this.skill();
        })

        document.addEventListener("keyup", e=>{
            if(e.code == "ArrowUp") this.keyArr[0] = false;
            if(e.code == "ArrowDown") this.keyArr[1] = false;
            if(e.code == "ArrowLeft") this.keyArr[2] = false;
            if(e.code == "ArrowRight") this.keyArr[3] = false;
            if(e.code == "KeyZ") this.keyArr[4] = false;
        })
    }

    fire(){
        if(this.currentFireTerm > 0) return;
        for(let i = 0; i<this.bulletLength; i++){
            App.app.getOrCreateBullet(this.bulletData[i].x, this.y, this.bulletSpeed, this.bulletData[i].v, 3, false, this.bulletDemage);
        }
        this.currentFireTerm = this.fireTerm;
        App.app.soundData.player.currentTime = 0;
        App.app.soundData.player.play();
    }

    update(time){
        if(this.currentFireTerm > 0) this.currentFireTerm -= time;
        let dy = 0, dx = 0;
        if(this.keyArr[0]) dy = -1;
        if(this.keyArr[1]) dy = 1;
        if(this.keyArr[2]) dx = -1;
        if(this.keyArr[3]) dx = 1;
        if(this.keyArr[4]) this.fire();
        if(this.keyArr[5]) this.shield();
        super.update(time, dx, dy);
        this.bulletData = [
            {
                x:this.x+this.w/2,
                y:this.y,
                v: new Vector(0, -1)
            },
            {
                x:this.x,
                y:this.y,
                v: new Vector(-1, -1)
            },
            {
                x:this.x+this.w,
                y:this.y,
                v: new Vector(1, -1)
            },
            {
                x:this.x+this.w / 2 - 15,
                y:this.y,
                v: new Vector(0, -1)
            },
            {
                x:this.x+this.w / 2 + 15,
                y:this.y,
                v: new Vector(0, -1)
            },
            {
                x:this.x,
                y:this.y,
                v: new Vector(0, -1)
            },
            {
                x:this.x+this.w,
                y:this.y,
                v: new Vector(0, -1)
            },
            {
                x:this.x,
                y:this.y,
                v: new Vector(1, -1)
            },
            {
                x:this.x+this.w,
                y:this.y,
                v: new Vector(-1, -1)
            }
        ]
    }

    checkOut(w, h){
        if(this.x < 0) this.x = 0;
        if(this.x + this.w >= w) this.x = w - this.w;
        if(this.y < 0) this.y = 0;
        if(this.y + this.h >= h) this.y = h - this.h;
    }

    skill(){
        if(this.shield <= 0 || this.shieldTime) return;
        this.shield--;
        this.shieldTime = true;
        setTimeout(()=>this.shieldTime = false, 5000);
    }

    render(ctx){
        if(this.shieldTime){
            ctx.fillStyle="rgba(0, 255, 0, 0.1)";
            ctx.beginPath();
            ctx.arc(this.x + this.w / 2, this.y + this.h / 2, 30, 0, Math.PI * 2);
            ctx.fill();
            ctx.closePath();
        }
        super.render(ctx);
        for(let i = 0; i<this.hp; i++){
            ctx.drawImage(this.heart, i * 40 + 10, App.app.canvas.height - 40, 25, 25);
        }
        ctx.drawImage(this.shiedl_img, App.app.canvas.width - 110, App.app.canvas.height - 50, 30, 40);
        ctx.font = "30px Airal";
        ctx.fillStyle="#ffffff";
        ctx.textBaseline = "middle";
        ctx.textAlign="center";
        ctx.fillText(`X ${this.shield}`, App.app.canvas.width - 50, App.app.canvas.height - 30);
        ctx.font = "15px Arial";
    }

    setDemage(value){
        this.hp -= value;
        if(this.hp <= 0){
            if(this.active){
                this.explosion();
                setTimeout(()=>App.app.gameOver(), 1200);
            }
        }
    }
}