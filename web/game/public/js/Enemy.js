class Enemy extends Game{
    constructor(){
        super();
        this.isBoss = null;
        this.bulletData = [];
        this.v = [];
        this.vIdx = 0;
    }

    reset(x, y, w, h, s, v, img, isBoss, hp, name){
        this.vIdx = 0;
        super.reset(x, y, w, h, s, v,img,hp);
        this.fire();
        this.isBoss = isBoss;
        this.maxX = this.vector[this.vIdx].maxX;
        this.maxY = this.vector[this.vIdx].maxY;
        this.name = name;
        this.v = v;
        this.time = 0;
    }

    fire(){
        if(!this.active) return false;
        this.bulletData.forEach(a=>{
            App.app.getOrCreateBullet(a.x, this.y+ this.h-5, 200, a.vector, 3, true, 1);
        })
        setTimeout(this.fire.bind(this), 2000);
    }

    update(time){
        if(!this.active) return;
        this.time += time;
        if(this.v[this.vIdx].time < this.time){
            if(this.isBoss){
                if(this.vIdx >= this.v.length - 1){
                    this.vIdx = 2;
                    this.time = 0;
                } else{
                    this.vIdx++;
                }
            } else {
                this.vIdx++;
            }
        }
        let normal = this.vector[this.vIdx].v.normalize();
        super.update(time, normal.x, normal.y);

        let data = new BulletData(this.x, App.app.canvas.width, App.app.canvas.height, this.w);
        this.bulletData = data.bulletData[this.name];
        if(this.isBoss) {
            if(normal.x < 0 && this.x < 0) this.x = 0;
            if(normal.y < 0 && this.y < 0) this.y = 0;
            if(normal.x > 0 && this.x > App.app.canvas.width - this.w) this.x = App.app.canvas.width - this.w;
            if(normal.y > 0 && this.y > App.app.canvas.height - this.h) this.y = App.app.canvas.height - this.h;
        }
        if(this.x < -this.w * 3 || this.y < -this.h * 5 || this.x > (this.w  + App.app.canvas.width) * 2 || this.y > (this.h+App.app.canvas.height)) this.active = false;
    }

    setDemage(value){
        super.setDemage(value);
        if(this.hp <= 0){
            if(this.isBoss){
                App.app.nextStage();
                this.itemDrop();
                App.app.setScore(20);
            } else{
                App.app.setScore(5);
            }
            this.itemDrop();
        }
    }

    itemDrop(){
        let rand = Math.floor(Math.random()*100);
        if(rand >= 40){
            let idx = Math.round(Math.random() * (App.app.itemData.length - 1));
            let item = App.app.itemData[idx];
            App.app.getOrCreateItem(this.x, this.y, item.w, item.h, item.img, item.skill);
        }
    }
}