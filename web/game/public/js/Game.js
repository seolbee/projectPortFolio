class Game{
    constructor(x, y, w, h, s, v, img, hp){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = s;
        this.vector = v;
        this.img = img;
        this.active = true;
        this.hp = hp;

        this.keyArr = [];
    }

    reset(x, y, w, h, s, v, img, hp){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = s;
        this.vector = v;
        this.img = img;
        this.hp = hp;
        this.active = true;
    }

    update(delta, dx, dy){
        if(!this.active) return;
        this.x += dx * delta * this.speed;
        this.y += dy * delta * this.speed;
    }

    render(ctx){
        if(!this.active) return;
        ctx.drawImage(this.img, this.x, this.y, this.w, this.h);
    }

    checkCollection(x, y, r){
        let centerX = this.x + this.w / 2;
        let centerY = this.y + this.h / 2;

        let d = Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2);
        return d < Math.pow(r + Math.min(this.w, this.h)/2, 2);
    }

    setDemage(value){
        this.hp -= value;
        if(this.hp <= 0){
            if(this.active){
                this.explosion();
            }
        }
    }

    explosion(){
        this.active = false;
        App.app.getOrCreateExplosion(this.x, this.y, 64, 64);
        App.app.soundData.explosion.currentTime = 0;
        App.app.soundData.explosion.play();
    }
}