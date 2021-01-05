class Bullet extends Game{
    constructor(x, y, s, v, demage, r){
        super(x,y,null,null,s,v,null,null);
        this.demage = demage;
        this.r = r;
    }

    reset(x, y, s, v, demage, r, isEnemy){
        super.reset(x, y, null, null, s, v, null, 0);
        this.demage = demage;
        this.r = r;
        this.isEnemy = isEnemy;
    }

    update(time){
        if(!this.active) return;
        let normal = this.vector.normalize();
        super.update(time, normal.x, normal.y);
        if(this.x < -this.r  || this.x > App.app.canvas.width + this.r || this.y < -this.r || this.y > App.app.canvas.height + this.r) this.active = false;
    }
    
    render(ctx){
        if(!this.active) return;
        ctx.beginPath();
        ctx.fillStyle="#f01616";
        ctx.arc(this.x, this.y, this.r + 2, 0, Math.PI * 2);
        ctx.closePath();
        ctx.fill();

        ctx.beginPath();
        ctx.fillStyle="#f8f860";
        ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2);
        ctx.closePath();
        ctx.fill();
    }
}