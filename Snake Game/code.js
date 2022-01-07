/* 
 * Auhor: Mitra Rouhipour
 * Finalproject course CPCS1045
 * Date: Dec 2020
 * 
 * HOW TO RUN:
 * - make sure the audio isenabled to hear the sound
 * - make sure audio files and js file is beside html file
 * - Open the index.html
 * 
 * Resources:
 * - Used labs and assignemtn code: keyword listeners, moving objects
 * - Audio files downoaded from https://freesound.org/ 
 * - To add audio file I modified the code from w3school to static html component
 *   https://www.w3schools.com/graphics/game_sound.asp
 *      Example:  
 *          <audio id="startSound">
 *               <source src="start.wav" type="audio/wav">
 *          </audio>
 * 
 * Description of the game:
 * This is a snake game, where snake is moving in one direction from a random start point 
 * until the player change it by arrow keys on keyboard.
 * 
 * The game has prizes and when the player eat/hit a target, the score increases based on target
 * value ratio and also the snake length increases to make the game more challening.
 * The scores and current prize values are shown on the right column beside the canvas. 
 * The prize color changes randomly from a set of three prizes (red, green, yellow)
 * and the color of prize's text is also chagned.
 * 
 * The player has 5 lives and score is accumulated. Each time the player hit the wall or an obstcles
 * or even itself then the player loose a live.
 * When lives is zero then the game is over, and a message is shown on the canvas.
 *
 * Obstcles are satic and they do not change. Walls are also obstcles.
 * 
 * */
const Up=1;
const Down=2;
const Left=3;
const Righ=4;

//block size, snake block
const BlockWidth = 15;
const BlockHeigth = 15;
// 720x600 -> 48x40 grid or blocks


let can = document.getElementById("drawingCanvas");
let context = can.getContext("2d");

//set default color
const BackgroundColor = "rgb(66, 66, 66)"; 
const ObstacleColor = "orange";
const PlayerColor ="blue"; //snake color
const PrizeColors = ["red", "yellow", "chartreuse"]; 

let interval;
  
//get sounds html elements
let hitSound = document.getElementById("hitSound");
let bonusSound = document.getElementById("bonusSound");
let startSound = document.getElementById("startSound");

//generate a random integer
function GetRandomInteger(a,b){
    let small;
    let big;
    if (a > b) {
        small = b 
        big = a
    }
    else {
        small = a,
        big = b;
    }

    let x = parseInt(Math.random() * (big - small+ 1) ) + small;
    return x;
} 

function ToRadians(degrees){
    return degrees * Math.PI / 180;
}

/* class block is aretangle of size of BlockWidth X BlockHeight
 * any element in this game is a bock or array of blocks: obstcles, walls, snake body and prizes
*/
class Block {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }

    //draw the block with a color given as input
    Draw(color) {
        context.beginPath();
   
        context.moveTo(this.x * BlockWidth ,this.y * BlockWidth);
        context.lineTo((this.x + 1) * BlockWidth, this.y * BlockHeigth);
        context.lineTo((this.x + 1) * BlockWidth, (this.y + 1) * BlockHeigth);
        context.lineTo(this.x * BlockWidth, (this.y + 1) * BlockHeigth);
        context.lineTo(this.x * BlockWidth, this.y * BlockHeigth);
        context.fillStyle = color;
        context.fill();
    }
}

/*
 * Class Player is the snake 
 * snake has 
 *  - start : start point / block
 *  - color : color of snake body  / player
 *  - lives : nmber of lives for each play
 *  - grow : is a number to grow the snake body after eating the prize
 *  - direction : is direction that the snake is moving, start direction is right
 *  - score : accumulated scores for a play
 *  - turned : ccontrol the turn and movement. If the snake has already turned direction 
 *             thei flag makes sure the snake moves one block in that direction before do another turn.
 *             It means, if the player chagned direction , he/she cannot make another turn until the snake move 
 *              one block toward new direction.
 * 
*/
class Player {
    constructor(lives, start, color) {
        this.start = start;
        this.color = color;
        this.lives = lives;
        this.grow = 5;
        this.body = [start];
        this.direction = Righ;
        this.score = 0;
        this.turned = false; 
        startSound.play(); //play the sound to start a game
        this.Refresh();    //set the html elemtns for lives and score
    }

    /* move snake body toward direction
     * For better performance, 
      * 1) add the head : simpley add a new block for the head
     *     and push it to the body fo snake
     * 2) remove the tail:  remove the last block from array (snake body)
     *    draw a block with background color 
     *
    */ 
    Move() {
        let head = this.Head();
        switch (this.direction) {
            case Up:
                head = new Block(head.x, head.y - 1);
                break;
            case Down:
                head = new Block(head.x, head.y + 1);
                break;
            case Left:
                head = new Block(head.x - 1, head.y);
                break;
            case Righ:
                head = new Block(head.x + 1, head.y);
                break;
        }
        this.body.push(head);
        head.Draw(this.color);
        if (this.grow > 0) {
            this.grow--;
        } else {
            let tail = this.body.shift(); //remove the tail from snake body (array of blocks)
            tail.Draw(BackgroundColor);   //panint the tail as background color
        }
        this.turned = false; // this avoid snake to turn direction until a move() is being called
        return head;
    }

    //retur the head of snake
    Head() {
        return this.body[this.body.length - 1];   
    }

    //refresh the html elements
    Refresh() {
        document.getElementById("lives").innerHTML = this.lives;
        document.getElementById("score").innerHTML = this.score;
    }

    //turn direction of moving snake
    Turn(dirction) {
        if (this.turned) {
            return;
        }
        let vertical = false;
        if(this.direction == Up || this.direction == Down) {
            vertical = true;
        }
        let toVertical = false;
        if (dirction == Up || dirction == Down) {
            toVertical = true;
        }
        if (vertical != toVertical) {
            this.direction = dirction;
        }
        this.turned = true;
    }

    //check the overlap or collison of a point with snake body. 
    //return true if ther eis a collision, otherwise return false
    Collides(point) {
        for (let i = 0; i < this.body.length - 1; i++) {
            if (this.body[i].x == point.x && this.body[i].y == point.y) {
                return true;
            }
        }
        return false;
    }

    /* This method increase the score based on input value, 
     * and grow the snake body
     */
   
    Eat(value) {
        bonusSound.play();
        this.score += 10 * value;
        this.grow += value;
        this.Refresh();
    }

    /* This funciton is called when the player loose and hit an obstcle or its own body
     *
    */
    Die() {
        hitSound.play();
        this.lives--; //decrease player lives
        this.Refresh(); //set html elements for lives and score
        if (this.lives > 0) { //if thre is lives then set initial prameters
            for (let i = 0; i < this.body.length; i++) {
                this.body[i].Draw(BackgroundColor); //remove the snake from screen by repaiting
            }
            this.grow = 5;
            this.body = [this.start];
            this.direction = Righ;  
            return false;
        }
        return true;
    }
}

/*
 *The game class has one player and an array of obstcles
 * the game checks the player movement and collision
 * 
 * parameter:
 *  - width : width of canvas
 *  - hegiht : height of canvas
 *  - player: is a player object / snake
 *  - obstcles: is array of obsccles
 *  - 
 *
*/
class Game {
    constructor(width, height) {
        this.width = width;
        this.height = height;
        this.player = new Player(3, new Block(5, 5), PlayerColor);
        this.obstacles = [ 
            new Obstacle(0, 0, width, 1, ObstacleColor),
            new Obstacle(0, height - 1, width, 1, ObstacleColor),
            new Obstacle(0, 0, 1, height, ObstacleColor),
            new Obstacle(width - 1, 1, width - 1, height - 1, ObstacleColor),
            new Obstacle(12, 10, 20, 1, ObstacleColor),
            new Obstacle(12, 30, 20, 1, ObstacleColor)
        ];

        //craw the game board and obstcles
        context.fillStyle = BackgroundColor;
        context.fillRect(0, 0, can.width, can.height);
        for (let i = 0; i < this.obstacles.length; i++) {
            this.obstacles[i].Draw();
        }
        this.GenerateTarget(); //generate a target or prize 
    }



    /* This funciton move player that repeatedly calls in intervals
     * checks if player collide obstcles and its own body
     * if there is no collision, then the palyer eats the prize, increase score, and snake body grows
     * if there is a collison, the palyer die. player's lives decreases.
    */
    Move() {
        let head = this.player.Move(); //move player in its current direction

        //check if snake head has collision with itself
        let colided = false;
        if (this.player.Collides(head)) {
            colided = true;
        }

        //if snake head has collison with obstcles
        for (let i = 0; i < this.obstacles.length; i++) {
            if (this.obstacles[i].Collides(head)) {
                colided = true;
            }
        }

        if (colided) { //if collision == true
            if (this.player.Die()) {
                this.End();
            } else {
                for (let i = 0; i < this.obstacles.length; i++) {
                    this.obstacles[i].Draw();
                }
            }
        }

        //check if the snake collid the prize then increase the score
        if (head.x == this.prize.x && head.y == this.prize.y) {
            this.player.Eat(this.prize.value);
            this.GenerateTarget(); //generate new target or prize on screen
        }
    }

    /*
     *This function  and draw a random block as a target with random value
     * it makes sure it does not overlap obscles, walls, and player / snake
     */
    GenerateTarget() {
        let colided = true;
        let point;
        while(colided) {
            let x = GetRandomInteger(1, this.width - 1);
            let y = GetRandomInteger(1, this.height - 1);
            point = new Block(x, y);
            colided = false;
            if (this.player.Collides(point)) {
                colided = true;
            }
            for (let i = 0; i < this.obstacles.length; i++) {
                if (this.obstacles[i].Collides(point)) {
                    colided = true;
                }
            }
        }
        this.prize = new Prize(point.x, point.y);
        this.prize.Draw();
    }

    //this funciton if the player loose (lvies == 0 )
    End() {
        context.font = "120px bold Arial";
        context.fillStyle = "red";
        context.textAlign = "center";
        context.fillText("Game Over", can.width/2, can.height/2); 
        clearInterval(inteval); //clear interval
    }
}

/*
 * This is obstcles calss in form of a rectangle with start and width and hegiht.
 * the width and height match with blockWidth and HeightSize of class Block
 * 
 * parameters:
 *  - x, y : top left corner of obstcle
 *  - width, height: width and height of obstcle
 *
*/
class Obstacle {
    constructor(x, y, width, height, color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // check collison of input point(block object) with the obstcle
    Collides(point) {
        if (this.x <= point.x && 
            point.x < this.x + this.width &&
            this.y <= point.y && 
            point.y < this.y + this.height) {
                return true;
            } else {
                return false;
            }
    }

    // draw obstcle, it is a rectangle
    Draw() {
        context.beginPath();
        context.rect(this.x * BlockWidth, this.y * BlockHeigth, this.width * BlockWidth, this.height * BlockHeigth);
        context.fillStyle = this.color;
        context.fill();          
    }
}

/*
 * Class Prize is used for showing the block on screen as target.
 * parameters:
 *  - x ,y: block position (with blockWidth and bblockHeight)
 *  - value: value is an random integer  of range [3,5]
 *  - color: color of each prize based on value
*/
class Prize {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.value = GetRandomInteger(3, 5);
        let prizeElement = document.getElementById("prize");
        prizeElement.innerHTML = this.value;
        this.color = PrizeColors[this.value - 3];
        prizeElement.setAttribute("style", "color:" + this.color + ";");
    }

    //draw prize block on screen
    Draw() {
        context.beginPath();
        context.arc(this.x * BlockWidth + BlockWidth / 2, this.y * BlockWidth + BlockHeigth / 2, BlockWidth / 2, ToRadians(0), ToRadians(360));
        context.closePath();
        context.fillStyle = this.color;
        context.fill();

    }
}


let game = new Game(48, 40);//game object or canvas
inteval = setInterval(Move, 100); 

function Move() {
    game.Move();    
}

//keyboard listener
let bodyElem = document.getElementById("body");
bodyElem.addEventListener("keydown", function(event) {
    if(event.key === "ArrowUp"){
        game.player.Turn(Up);
    }

    if(event.key === "ArrowDown"){
        game.player.Turn(Down);
    }

    if(event.key === "ArrowLeft"){
        game.player.Turn(Left);
    }

    if(event.key === "ArrowRight"){
        game.player.Turn(Righ);
    }

    if(event.key === "Escape") {
        game.End();
    }
});

//restart the game
function StartGame() {   
    game.End(); 
    game = new Game(48, 40);
    inteval = setInterval(Move, 100);   
}
