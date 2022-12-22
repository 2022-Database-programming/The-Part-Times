<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>STOPWATCH</title>
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Boogaloo&display=swap');

    body {
        font-family: 'Boogaloo', cursive;
    }

    #micro {
        font-size: medium;
    }
    
    p {
        font-size: 50px;
    }

    button {
        background-color: whitesmoke;
        color: rgb(17, 5, 5);
        font-family: 'Boogaloo', cursive;

    }

    .w-btn {
        position: relative;
        border: none;
        display: inline-block;
        padding: 10px 20px;
        border-radius: 15px;
        font-family: "paybooc-Light", sans-serif;
        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        text-decoration: none;
        font-weight: 600;
        transition: 0.25s;
    }

    .w-btn-indigo {
        background-color: aliceblue;
        color: #1e6b7b;
    }

    .w-btn-green {
        background-color: #77af9c;
        color: #d7fff1;
    }

</style>
<body>
    <div style="text-align: center;">
        <p>Stopwatch</p>
        <h1><span id="hour">00</span>:<span id="min">00</span>:<span id="sec">00</span>.<span id="micro">00</span></h1>
        <button class="w-btn w-btn-green" id="start">start</button>
        <button class="w-btn w-btn-indigo" id="stop">stop</button>
        <button class="w-btn w-btn-indigo" id="clear">clear</button>
    </div>
    <script>
        window.onload = function(){
            
            let timer_sec;
            let timer_min;
            let timer_hour;
            let timer_micro;

            let timer = 0;

            //click start button
            document.getElementById("start").addEventListener("click", function(){
                //console.log(timer);
                if(timer > 0){
                    return;
                }

                var micro = parseInt(document.getElementById("micro").innerText);
                var sec = parseInt(document.getElementById("sec").innerText);
                var min = parseInt(document.getElementById("min").innerText);
                var hour = parseInt(document.getElementById("hour").innerText);

                //start seconds
                timer_micro = setInterval(function(){
                    micro++;
                    if(micro == 100) {
                        micro = "00";
                    } else if(micro < 10){
                        micro = "0" + micro;
                    }
                    document.getElementById("micro").innerText = micro;
                }, 10);

                //start seconds
                timer_sec = setInterval(function(){
                    //console.log(i);
                    sec++;
                    if(sec == 60) {
                        sec = "00";
                    } else if(sec < 10){
                        sec = "0" + sec;
                    }
                    document.getElementById("sec").innerText = sec;
                }, 1000);

                //start minutes
                timer_min = setInterval(function(){
                    min++;

                    if(min == 60) {
                        min = 0;
                    } else if(min < 10){
                        min = "0" + min;
                    }

                    document.getElementById("min").innerText = min;
                }, 60000);

                //start hours
                timer_hour = setInterval(function(){
                    //console.log(hour);
                    hour++;

                    if(hour < 10){
                        hour = "0" + hour;
                    }

                    document.getElementById("hour").innerText = hour;

                }, 3600000);

                timer++;
                //console.log(timer);
            });

            //click stop button
            document.getElementById("stop").addEventListener("click", function(){
                stop();
            });

            function stop(){
                clearInterval(timer_micro);
                clearInterval(timer_sec);
                clearInterval(timer_min);
                clearInterval(timer_hour);

                timer--;
                if(timer < 0)
                    timer = 0;
            }

            //click clear button
            document.getElementById("clear").addEventListener("click", function(){
                stop();
                document.getElementById("micro").innerText = "00";
                document.getElementById("sec").innerText = "00";
                document.getElementById("min").innerText = "00";
                document.getElementById("hour").innerText = "00";
            });
        }
    </script>
</body>
</html>