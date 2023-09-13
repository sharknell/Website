async function showAvatar() {

    //{
    //"name": "Violet-Bora-Lee",
    //"isAdmin": true
    //}
    // JSON 읽기
    //let response = await fetch('/article/promise-chaining/user.json');
    //let user = await response.json();

    //console.log(user);
  
    // github 사용자 정보 읽기
    let githubResponse = await fetch(`https://api.github.com/users/Violet-Bora-Lee`);
    let githubUser = await githubResponse.json();
  
    console.log(githubUser);
    // 아바타 보여주기
    let img = document.createElement('img');
    img.src = githubUser.avatar_url;
    img.className = "promise-avatar-example";
    document.body.append(img);
  
    // 3초 대기
    await new Promise((resolve, reject) => setTimeout(resolve, 3000));
  
    img.remove();
  
    return githubUser;
  }
  
  showAvatar();