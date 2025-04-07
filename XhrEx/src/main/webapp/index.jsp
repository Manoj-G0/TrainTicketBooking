<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	Enter a movie : <input id = "movie"> <button onclick="getImdb()">Search</button>
	<div id = "res">
	</div>
	<script type="text/javascript">
	
		function getImdb()
		{
			document.getElementById("res").innerHTML = "";
			let name = document.getElementById("movie").value;
			const data = null;

			const xhr = new XMLHttpRequest();
			xhr.withCredentials = true;

			xhr.addEventListener('readystatechange', function () {
				if (this.readyState === this.DONE) {
					
					const list = JSON.parse(xhr.responseText);
					console.log(list);
					
					list.forEach(l => {
						console.log(name,l.originalTitle);
						if(name === l.originalTitle)
						{
							const p = document.createElement("h3");
							const image = document.createElement("img");
							const r = document.createElement("h4");
							image.src = l.primaryImage;
							image.height = 200;
							image.width = 200;
							p.innerText = "Title : "
							p.innerHTML += l.originalTitle;
							r.innerText = "Rating : "
							r.innerHTML += l.averageRating;
							
							document.getElementById("res").appendChild(p);
							document.getElementById("res").appendChild(image);
							document.getElementById("res").appendChild(r);
						}
						
						
						
					})
					
				}
			});

			xhr.open('GET', 'https://imdb236.p.rapidapi.com/imdb/lowest-rated-movies');
			xhr.setRequestHeader('x-rapidapi-key', '0eaf3dc268msh7798f8f75b49eb2p150da7jsn8afa517b3656');
			xhr.setRequestHeader('x-rapidapi-host', 'imdb236.p.rapidapi.com');

			xhr.send(data);
		}
		
	</script>
</body>
</html>