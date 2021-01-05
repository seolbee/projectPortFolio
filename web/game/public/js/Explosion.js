class Explosion extends Game{
    constructor(x, y, w, h, img){
        super(x, y, w, h, null, null, img, null);
        this.now = 0;
        this.duration = 1.2;
        this.idx = 0;
        this.active = true;
    }

    reset(x, y, w, h, img){
        super.reset(x, y, w, h , null, null, img, null);
        this.now = 0;
        this.idx = 0;
        this.active = true;
    }
    
    update(time){
        if(!this.active) return;
        this.now += time;
        this.idx = Math.floor(24 * this.now / this.duration);
        if(this.now > this.duration){
            this.active = false;
        }
    }

    render(ctx){
        if(!this.active) return;
        let sx = this.idx % 5 * 64;
        let sy = Math.floor(this.idx / 5) * 64;
        ctx.drawImage(this.img, sx, sy, 64, 64, this.x, this.y, this.w, this.w, this.h);
    }
}

//https://imgbin.com/download-png/wYiBVKrC 
//위 사이트에서 다운 받기