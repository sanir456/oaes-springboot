async function login()
{
    const UserName = document.getElementById("userName").value;
    const Password = document.getElementById("password").value;
    fetch('http://localhost:8080/user/login',{
        method: 'POST',
        headers:{
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            userName:UserName,
            password:Password
        })
    }).then(response => response.json())
        .then((data) => {
            if(data)
            {
                document.getElementById("loginBody").style.display = "none";
                document.getElementById("homeBody").style.display = "block";
            }
            else
            {
                document.getElementById("msg").innerHTML="Username or Password are wrong!!"
            }
        });
}