class App{
    constructor(){
        this.canvas = document.querySelector("#myGame");
        this.ctx = this.canvas.getContext("2d");
        this.imageList = {};
        this.backList = [];
        this.bulletList = [];
        this.enemyList = [];
        this.explosionList = [];
        this.startTime = false;
        this.start = false;
        this.stageData = [];
        this.itemList = [];
        this.over = false;
        this.clear = false;
        this.itemData = [];
        this.isStage = false;
        this.soundData = {};
        this.score = 0;
        App.app = this;
        this.init();
    }

    async init(){
        this.imageList.back = await this.loadImage("back.png");
        this.imageList.player = await this.loadImage("player.png");
        this.imageList.enemy1 = await this.loadImage("enemy1.png");
        this.imageList.enemy2 = await this.loadImage("enemy2.png");
        this.imageList.enemy3 = await this.loadImage("enemy3.png");
        this.imageList.enemy4 = await this.loadImage("enemy5.png");
        this.imageList.enemy5 = await this.loadImage("enemy6.png");
        this.imageList.enemy6 = await this.loadImage("enemy10.png");
        this.imageList.enemy7 = await this.loadImage("enemy9.png");
        this.imageList.explosion = await this.loadImage("explosion.png");
        this.imageList.heart = await this.loadImage("heart1.png");
        this.imageList.bullet_item = await this.loadImage("bullet_item.png");
        this.imageList.fuel = await this.loadImage("fuel.png");
        this.imageList.shield = await this.loadImage("shield.png");
        this.soundData.player = await this.loadMusic("Pew__001.ogg");
        this.soundData.back = await this.loadMusic("background.ogg");
        this.soundData.explosion = await this.loadMusic("explosion.wav");
        this.soundData.back.loop = true;
        for(let i = 0; i<3; i++){
            this.backList.push(new Background(0, -i * this.canvas.height, this.canvas.width, this.canvas.height, 40, null, this.imageList.back));
        }
        this.player = new Player();
        this.player.init();
        let stage = new Stage(this.canvas.width, this.canvas.height, this.imageList);
        this.stageData = stage.stage;
        this.itemData = stage.itemData;
        this.event();
        this.intro();
        requestAnimationFrame(this.frame.bind(this));
    }

    event(){
        this.canvas.addEventListener("click", (e)=>this.click_start(e));
    }

    getOrCreateBullet(x, y, s, v, r, isEnemy, demage){
        let bullet = this.bulletList.find(x=>!x.active);
        if(bullet == undefined){
            bullet = new Bullet();
            this.bulletList.push(bullet);
        } 
        bullet.reset(x, y, s, v , demage, r, isEnemy);
    }

    getOrCreateEnemy(data){
        let enemy = this.enemyList.find(e=>!e.active);
        if(enemy === undefined){
            enemy = new Enemy();
            this.enemyList.push(enemy);
        }
        enemy.reset(data.x, data.y, data.w, data.h, data.s, data.v, data.imgs, data.b, data.hp, data.name);
    }

    getOrCreateExplosion(x, y, w, h){
        let explosion = this.explosionList.find(x=>!x.active);
        if(explosion === undefined){
            explosion = new Explosion();
            this.explosionList.push(explosion);
        }
        explosion.reset(x, y, w, h, this.imageList.explosion);
    }

    getOrCreateItem(x, y, w, h, img, skill){
        let item = this.itemList.find(x=>!x.active);
        if(item== undefined){
            item = new Item();
            this.itemList.push(item);
        }

        item.reset(x, y, w, h, img, 5, "a", new Vector(0, 1), 50, skill);
    }

    loadImage(name){
        return new Promise((res)=>{
            let img = new Image();
            img.src = `/images/${name}`;
            img.addEventListener("load", ()=>res(img));
        })
    }

    loadMusic(name){
        return new Promise((res)=>{
            let music = new Audio();
            music.src = `/sound/${name}`;
            res(music);
        })
    }

    frame(delta){
        if(!this.startTime) this.startTime = delta;
        let time = delta - this.startTime;
        this.startTime = delta;
        this.update(time / 1000);
        this.render();
        requestAnimationFrame(this.frame.bind(this));
    }

    update(time){
        this.backList.forEach(x=>x.update(time));
        if(this.backList[0].y > this.canvas.height){
            let back = this.backList.shift();
            back.y = this.backList[this.backList.length - 1].y - this.canvas.height;
            this.backList.push(back);
        }
        if(!this.start) return;
        this.gameTime += time;
        let nowEnemy = this.stageData[this.stageArrIdx][this.stageObjIdx];
        if(nowEnemy !== undefined && nowEnemy.time <= this.gameTime ){
            this.getOrCreateEnemy(nowEnemy.data);
            this.stageObjIdx++;
        }
        if(!this.start) return;
        this.bulletList.filter(x=>x.active).forEach(b=>{
            if(!b.isEnemy){
                this.enemyList.filter(x=>x.active).forEach(e=>{
                    if(e.checkCollection(b.x, b.y, b.r) && this.start){
                        e.setDemage(b.demage);
                        b.active = false;
                    }
                })
            } else{
                if(this.player.checkCollection(b.x, b.y, b.r) && this.start){
                    if(!this.player.shieldTime){
                        this.player.setDemage(b.demage);
                    }
                    b.active = false;
                }
            }
        })
        this.itemList.filter(i=>i.active).forEach(i=>{
            if(i.checkCollection(this.player.x, this.player.y, this.player.w, this.player.h)){
                i.itemSkill(this.player);
                i.active = false;
            }
        })
        this.player.update(time);
        this.player.checkOut(this.canvas.width, this.canvas.height);
        this.bulletList.forEach(b=>b.update(time));
        this.enemyList.forEach(e=>e.update(time));
        this.explosionList.forEach(e=> e.update(time));
        this.itemList.forEach(i=>i.update(time));
    }

    render(){
        if(!this.start || this.clear || this.over) return;
        this.ctx.clearRect(0,0, this.canvas.width, this.canvas.height);
        this.backList.forEach(x=>x.render(this.ctx));
        this.player.render(this.ctx);
        this.bulletList.forEach(b=>b.render(this.ctx));
        this.enemyList.forEach(e=>e.render(this.ctx));
        this.explosionList.forEach(e=>e.render(this.ctx));
        this.itemList.forEach(i=> i.render(this.ctx));
        this.scorerender(this.ctx);
        if(this.isStage){
            this.stage();
        }
    }

    scorerender(ctx){
        ctx.fillStyle="#ffffff";
        ctx.font = "20px Arial";
        ctx.textAlign = "center";
        ctx.textBaseline = "middle";
        ctx.fillText(`Score : ${this.score}`, 50, 30);
    }

    gameStart(){
        this.player.reset(this.canvas.width / 2 - 30, this.canvas.height - 50, 60, 40, 300, null, this.imageList.player, 3, this.imageList.heart, 3, this.imageList.shield);
        this.stageObjIdx = 0;
        this.stageArrIdx = 0;
        this.gameTime = 0;
        this.isStage = true;
        this.soundData.back.currentTime = 0;
        this.soundData.back.play();
        this.score = 0;
    }

    reset(){
        return new Promise((res)=>{
            this.bulletList.forEach(x=> x.active = false);
            this.itemList.forEach(i=>i.active = false);
            this.enemyList.forEach(e=> e.active = false);
            this.explosionList.forEach(e=>e.active = false); 
        })
    }

    click_start(e){
        new Promise((res)=>{
            if(!this.start){
                this.start = true;
                this.over = false;
                this.clear = false;
                this.gameStart();
            } else return;
        })
    }

    intro(){
        this.start = false;
        this.ctx.fillStyle="#000000";
        this.ctx.fillRect(0,0, this.canvas.width, this.canvas.height);
        this.ctx.font = "50px Geo";
        this.ctx.fillStyle="#ffffff";
        this.ctx.textAlign="center";
        this.ctx.textBaseline ="middle";
        this.ctx.fillText("Shooting Space", this.canvas.width / 2, this.canvas.height / 2 - 50);
        this.ctx.font="18px Geo";
        this.ctx.fillText("click and the play game", this.canvas.width / 2, this.canvas.height / 2 + 100);
    }

    async GameClear(){
        this.soundData.back.pause();
        this.clear = true;
        this.ctx.fillStyle ="#000000";
        this.ctx.fillRect(0,0, this.canvas.width, this.canvas.height);
        this.ctx.fillStyle="#ffffff";
        this.ctx.font = "50px Geo";
        this.ctx.fillText("Game Clear", this.canvas.width / 2, this.canvas.height / 2-50);
        this.ctx.font = "25px Geo";
        this.ctx.fillText(`Score : ${this.score}`, this.canvas.width / 2, this.canvas.height / 2 + 10);
        this.sendScore();
        await this.reset();
        setTimeout(()=>this.intro(), 1200);
    }

    async gameOver(){
        this.soundData.back.pause();
        this.over = true;
        this.ctx.fillStyle="#000000";
        this.ctx.fillRect(0,0, this.canvas.width, this.canvas.height);
        this.ctx.fillStyle="#ff0000";
        this.ctx.font = "50px Geo";
        this.ctx.fillText("Game Over", this.canvas.width / 2, this.canvas.height / 2 -50);
        this.ctx.fillStyle="#ffffff";
        this.ctx.font = "25px Geo";
        this.ctx.fillText(`Score : ${this.score}`, this.canvas.width / 2, this.canvas.height / 2 + 10);
        this.sendScore();
        await this.reset();
        setTimeout(()=> this.intro(), 1200);
    }

    nextStage(){
        if(this.stageArrIdx >= this.stageData.length- 1){
            setTimeout(()=>App.app.GameClear(), 1200);
        } else{
            setTimeout(()=>{
                this.stageArrIdx++;
                this.stageObjIdx = 0;
                this.gameTime = 0;
                this.isStage = true;
            }, 3000)
        }
    }

    stage(){
        this.ctx.fillStyle="#ffffff";
        this.ctx.font = "30px Arial";
        this.ctx.textAlign = "center";
        this.ctx.textBaseline = "middle";
        this.ctx.fillText(`Stage ${this.stageArrIdx + 1}`, 100, this.canvas.height / 2 + 200);
        setTimeout(()=>this.isStage = false, 2000);
    }

    setScore(value){
        this.score += value;
    }

    sendScore(){
        let form = new FormData();
        form.append("score", this.score);
        fetch("/board/rank", {
            method: "post",
            body : form
        })
    }
}