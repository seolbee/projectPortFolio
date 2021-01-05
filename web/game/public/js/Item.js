class Item extends Game{
    constructor(x, y, w, h, img, a, time, v, s){
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = img;
        this.active = true;
        this.attribute = a;
        this.time = time;
        this.vector = v;
        this.speed = s;
    }

    reset(x, y, w, h, img, a, time, v, s, skill){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = img;
        this.active = true;
        this.time = time;
        this.attribuet = a;
        this.vector = v;
        this.speed = s;
        this.skill = skill;
    }

    update(time){
        let normal = this.vector.normalize();
        super.update(time, normal.x, normal.y);
        if(this.x >= App.app.canvas.width || this.y >= App.app.canvas.height){
            this.active = false;
        }
    }

    render(ctx){
        super.render(ctx);
    }

    itemSkill(player){
        this.skill(player);
    }

    checkCollection(x, y, w, h){
        if(x < this.x + this.w && y < this.y + this.h && x + w > this.x && y + h > this.y){
            return true;
        } else{
            return false;
        }
    }
}