//Size of the grid
var size = 4;
var min = 0;
var max = size - 1;

var isMoved = false;
var score = 0;
let totalScore = 0;

var excludeIds = [];

function load() {
    //alert("load");
    //Load the table
    var html = '<table style="border:2px solid black;margin-left:auto;margin-right:auto;">';
    for (var row = 0; row < size; row++) {
        html += '<tr style="border:1px solid black;">';
        for (var col = 0; col < size; col++) {
            var id = row + "" + col;
            html += '<td style="border:1px solid black;" align="left" valign="top" height="50" width="50" id="' + id + '"></td>';
        }
        html += '</tr>';
    }
    html += '</table>';
    //alert(html);
    document.getElementById("canvas").innerHTML = html;

    var id1 = getId();
    var id2 = "";
    while (true) {
        id2 = getId();
        if (id1 != id2)
            break;
    }
    //Set initial 2 values
    document.getElementById(id1).innerHTML = "2";
    document.getElementById(id2).innerHTML = "4";

    document.getElementById(id1).style.backgroundImage = getElement(2);
    document.getElementById(id2).style.backgroundImage = getElement(4);

    score = 0;
    document.getElementById("score").innerHTML = score;

    return false;
}

function getRandom() {
    return Math.floor(Math.random() * (max - min + 1) + min);
}

function getId() {
    var i = getRandom();
    var j = getRandom();
    return i + "" + j;
}

function up() {
    isMoved = false;
    excludeIds = [];
    for (var j = min; j <= max; j++) {
        for (var i = min; i <= max; i++) {
            var id = i + "" + j;
            if (document.getElementById(id).innerHTML != "") {
                moveUp(id);
            }
        }
    }
    if (isMoved == true) {
        update();
    }
    return false;
}

function moveUp(id) {
    if (!id.startsWith(min)) {
        var arr = id.split("");
        var i = parseInt(arr[0]);
        var j = parseInt(arr[1]);
        for (var k = (i - 1); k >= min; k--) {
            var nId = k + "" + j;
            if (document.getElementById(nId).innerHTML != "") {
                var val = parseInt(document.getElementById((k + 1) + "" + j).innerHTML);
                var nVal = parseInt(document.getElementById(nId).innerHTML);
                if (val == nVal) {
                    if (excludeIds.indexOf(nId) == -1) {
                        excludeIds.push(nId);
                        document.getElementById(nId).innerHTML = (val + nVal);
                        document.getElementById(nId).style.backgroundImage = getElement((val + nVal));
                        document.getElementById((k + 1) + "" + j).innerHTML = "";
                        document.getElementById((k + 1) + "" + j).style.backgroundImage = "#url('images/default.png')";
                        isMoved = true;
                        score += (val + nVal);
                    }
                    break;
                }
            } else {
                document.getElementById(nId).innerHTML = document.getElementById((k + 1) + "" + j).innerHTML;
                document.getElementById(nId).style.backgroundImage = document.getElementById((k + 1) + "" + j).style.backgroundImage;
                document.getElementById((k + 1) + "" + j).innerHTML = "";
                document.getElementById((k + 1) + "" + j).style.backgroundImage = "url('images/default.png')";
                isMoved = true;
            }
        }
    }
    return false;
}

function left() {
    isMoved = false;
    excludeIds = [];
    for (var i = min; i <= max; i++) {
        for (var j = min; j <= max; j++) {
            var id = i + "" + j;
            if (document.getElementById(id).innerHTML != "") {
                moveLeft(id);
            }
        }
    }
    if (isMoved == true) {
        update();
    }
    return false;
}

function moveLeft(id) {
    if (!id.endsWith(min)) {
        var arr = id.split("");
        var i = parseInt(arr[0]);
        var j = parseInt(arr[1]);
        for (var k = (j - 1); k >= min; k--) {
            var nId = i + "" + k;
            if (document.getElementById(nId).innerHTML != "") {
                var val = parseInt(document.getElementById(i + "" + (k + 1)).innerHTML);
                var nVal = parseInt(document.getElementById(nId).innerHTML);
                if (val == nVal) {
                    if (excludeIds.indexOf(nId) == -1) {
                        excludeIds.push(nId);
                        document.getElementById(nId).innerHTML = (val + nVal);
                        document.getElementById(nId).style.backgroundImage = getElement((val + nVal));
                        document.getElementById(i + "" + (k + 1)).innerHTML = "";
                        document.getElementById(i + "" + (k + 1)).style.backgroundImage = "url('images/default.png')";
                        isMoved = true;
                        score += (val + nVal);
                    }
                    break;
                }
            } else {
                document.getElementById(nId).innerHTML = document.getElementById(i + "" + (k + 1)).innerHTML;
                document.getElementById(nId).style.backgroundImage = document.getElementById(i + "" + (k + 1)).style.backgroundImage;
                document.getElementById(i + "" + (k + 1)).innerHTML = "";
                document.getElementById(i + "" + (k + 1)).style.backgroundImage = "url('images/default.png')";
                isMoved = true;
            }
        }
    }
    return false;
}

function down() {
    isMoved = false;
    excludeIds = [];
    for (var i = min; i <= max; i++) {
        for (var j = max; j >= min; j--) {
            var id = j + "" + i;
            if (document.getElementById(id).innerHTML != "") {
                moveDown(id);
            }
        }
    }
    if (isMoved == true) {
        update();
    }
    return false;
}

function moveDown(id) {
    if (!id.startsWith(max)) {
        var arr = id.split("");
        var i = parseInt(arr[0]);
        var j = parseInt(arr[1]);
        for (var k = (i + 1); k <= max; k++) {
            var nId = k + "" + j;
            if (document.getElementById(nId).innerHTML != "") {
                var val = parseInt(document.getElementById((k - 1) + "" + j).innerHTML);
                var nVal = parseInt(document.getElementById(nId).innerHTML);
                if (val == nVal) {
                    if (excludeIds.indexOf(nId) == -1) {
                        excludeIds.push(nId);
                        document.getElementById(nId).innerHTML = (val + nVal);
                        document.getElementById(nId).style.backgroundImage = getElement((val + nVal));
                        document.getElementById((k - 1) + "" + j).innerHTML = "";
                        document.getElementById((k - 1) + "" + j).style.backgroundImage = "url('images/default.png')";
                        isMoved = true;
                        score += (val + nVal);
                    }
                    break;
                }
            } else {
                document.getElementById(nId).innerHTML = document.getElementById((k - 1) + "" + j).innerHTML;
                document.getElementById(nId).style.backgroundImage = document.getElementById((k - 1) + "" + j).style.backgroundImage;
                document.getElementById((k - 1) + "" + j).innerHTML = "";
                document.getElementById((k - 1) + "" + j).style.backgroundImage = "url('images/default.png')";
                isMoved = true;
            }
        }
    }
    return false;
}

function right() {
    isMoved = false;
    excludeIds = [];
    for (var i = min; i <= max; i++) {
        for (var j = max; j >= min; j--) {
            var id = i + "" + j;
            if (document.getElementById(id).innerHTML != "") {
                moveRight(id);
            }
        }
    }
    if (isMoved == true) {
        update();
    }
    return false;
}

function moveRight(id) {
    if (!id.endsWith(max)) {
        var arr = id.split("");
        var i = parseInt(arr[0]);
        var j = parseInt(arr[1]);
        for (var k = (j + 1); k <= max; k++) {
            var nId = i + "" + k;
            if (document.getElementById(nId).innerHTML != "") {
                var val = parseInt(document.getElementById(i + "" + (k - 1)).innerHTML);
                var nVal = parseInt(document.getElementById(nId).innerHTML);
                if (val == nVal) {
                    if (excludeIds.indexOf(nId) == -1) {
                        excludeIds.push(nId);
                        document.getElementById(nId).innerHTML = (val + nVal);
                        document.getElementById(nId).style.backgroundImage = getElement((val + nVal));
                        document.getElementById(i + "" + (k - 1)).innerHTML = "";
                        document.getElementById(i + "" + (k - 1)).style.backgroundImage = "url('images/default.png')";
                        isMoved = true;
                        score += (val + nVal);
                    }
                    break;
                }
            } else {
                document.getElementById(nId).innerHTML = document.getElementById(i + "" + (k - 1)).innerHTML;
                document.getElementById(nId).style.backgroundImage = document.getElementById(i + "" + (k - 1)).style.backgroundImage;
                // document.getElementById(nId).style.backgroundColor = document.getElementById(i+""+(k-1)).style.backgroundColor;
                document.getElementById(i + "" + (k - 1)).innerHTML = "";
                document.getElementById(i + "" + (k - 1)).style.backgroundImage = "url('images/default.png')";
                // document.getElementById(i+""+(k-1)).style.backgroundColor = "#ffffff";
                isMoved = true;
            }
        }
    }
    return false;
}

function update() {
    //Add new value
    var ids = [];
    for (var i = min; i <= max; i++) {
        for (var j = min; j <= max; j++) {
            var id = i + "" + j;
            if (document.getElementById(id).innerHTML == "") {
                ids.push(id);
            }
        }
    }
    var id = ids[Math.floor(Math.random() * ids.length)];
    var newNumber = Math.floor(Math.random() * 3) * 2
    if (newNumber === 0) {
        newNumber = 2
    }
    document.getElementById(id).innerHTML = newNumber;
    if (newNumber === 2) {
        document.getElementById(id).style.backgroundImage = getElement(2);
    } else {
        document.getElementById(id).style.backgroundImage = getElement(4);
    }

    //Check if no move space available
    var allFilled = true;
    for (var i = min; i <= max; i++) {
        for (var j = min; j <= max; j++) {
            var id = i + "" + j;
            if (document.getElementById(id).innerHTML == "") {
                allFilled = false;
                break;
            }
        }
    }
    //Update score
    document.getElementById("score").innerHTML = score;
    if (allFilled) {
        checkGameOver();
    }
}

function checkGameOver() {
    var isOver = true;
    for (var j = min; j <= max; j++) {
        for (var i = min; i <= (max - 1); i++) {
            //alert(i+" "+j);
            var val = parseInt(document.getElementById(i + "" + j).innerHTML);
            var nVal = parseInt(document.getElementById((i + 1) + "" + j).innerHTML);
            if (val == nVal) {
                isOver = false;
                break;
            }
        }
    }
    if (isOver == true) {
        for (var i = min; i <= max; i++) {
            for (var j = min; j <= (max - 1); j++) {
                //alert(i+" "+j);
                var val = parseInt(document.getElementById(i + "" + j).innerHTML);
                var nVal = parseInt(document.getElementById(i + "" + (j + 1)).innerHTML);
                if (val == nVal) {
                    isOver = false;
                    break;
                }
            }
        }
    }
    if (isOver) {
        alert("Игра окончена!  " +
            "Ваш счёт: " + score);
        totalScore = score;
        console.log(totalScore);
        saveScore();

    }
    return false;
}

function saveScore(){
    const currentScore = {
        myScore: totalScore
    };
    fetch("/two-o-four-eight-application/save", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json; charset=UTF-8",
            //hardcoded!
            "AuthorizationToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE2MTk5MzI4NTcsImV4cCI6MTYyMDc5Njg1N30.nBI5gbJYOAoA9cyHXKk2yFj4y2Z8-R61AzaVqjAXNwSn-nupSID6-lxgix0Nd4RJsZChTpgmPrYYsBPLSRug7w"
        },
        body: JSON.stringify(currentScore)
    })
}

// function getColor(val)
// {
// 	var color = "#ffffff";
// 	switch(val) {
// 		case 2:		color = "#A1F7A4"; break;
// 		case 4:		color = "#A1F7EF"; break;
// 		case 8:		color = "#EF776F"; break;
// 		case 16:	color = "#ECD2B1"; break;
// 		case 32:	color = "#A29C19"; break;
// 		case 64:	color = "#A1F7A4"; break;
// 		case 128:	color = "#A1F7A4"; break;
// 		case 256:	color = "#A1F7A4"; break;
// 		case 512:	color = "#F7F75A"; break;
// 		case 1024:	color = "#A1F7EF"; break;
// 		case 2048:	color = "#EF776F"; break;
// 		default:	color = "#ffffff";
// 	}
// 	return color;
// }

function getElement(val) {
    var element = "url('images/default.png')";
    switch (val) {
        case 2:
            element = "url('images/H.png')";
            break;
        case 4:
            element = "url('images/He.png')";
            break;
        case 8:
            element = "url('images/Li.png')";
            break;
        case 16:
            element = "url('images/Be.png')";
            break;
        case 32:
            element = "url('images/B.png')";
            break;
        case 64:
            element = "url('images/C.png')";
            break;
        case 128:
            element = "url('images/N.png')";
            break;
        case 256:
            element = "url('images/O.png')";
            break;
        case 512:
            element = "url('images/F.png')";
            break;
        case 1024:
            element = "url('images/Ne.png')";
            break;
        case 2048:
            element = "url('images/Na.png')";
            break;
        case 4096:
            element = "url('images/Mg.png')";
            break;
        case 8192:
            element = "url('images/Al.png')";
            break;
        default:
            element = "url('images/default.png')";
    }
    return element;
}


if (typeof String.prototype.startsWith != 'function') {
    String.prototype.startsWith = function(str) {
        return this.substring(0, str.length) === str;
    }
};
if (typeof String.prototype.endsWith != 'function') {
    String.prototype.endsWith = function(str) {
        return this.substring(this.length - str.length, this.length) === str;
    }
};
document.onkeydown = function(e) {
    e.preventDefault(); //to prevent scroll of screen
    switch (e.keyCode) {
        case 37:
            left();
            break;
        case 38:
            up();
            break;
        case 39:
            right();
            break;
        case 40:
            down();
            break;
    }
};
//calling load method
load();
//-->