$(document).ready(
		function() {

			var index = 0;

			$('#addInterest').click(
					function() {
						var ul = $("#interestsList")[0];
						var duplicateFound = false;
						var candidate = document
								.getElementById("interestInput");
						if (/[^a-zA-Z0-9 ]/.test(candidate.value)
								|| candidate.value === undefined
								|| candidate.value.trim() === "") {
							return;
						}
						var lowerCaseValue = candidate.value.trim()
								.toLowerCase();
						for (var i = 0; i < ul.children.length; i++) {
							if (lowerCaseValue == ul.children[i].innerText
									.trim()) {
								return;
							}
						}
						if (index == 0) {
							index = Math.floor(ul.children.length / 2);
						}
						var form = document.createElement("input");
						form.setAttribute('name', "interests[" + index
								+ "].interest");
						form.setAttribute('type', "hidden");
						form.setAttribute('value', lowerCaseValue);
						form.setAttribute("id", "input-" + lowerCaseValue);
						var li = document.createElement("li");
						li.setAttribute('id', "entry-" + candidate.value);
						li.setAttribute('class', "interest");
						var div = document.createElement("div");
						div.setAttribute('class', 'interest-value');
						div
								.appendChild(document
										.createTextNode(lowerCaseValue));
						var removeButton = document.createElement("button");
						removeButton.setAttribute("onClick", "removeInterest('"
								+ lowerCaseValue + "')");
						removeButton.setAttribute("type", "button");
						removeButton.setAttribute("class",
								"glyphicon glyphicon-remove remove-button");
						li.appendChild(div);
						div.appendChild(removeButton);
						ul.appendChild(li);
						ul.appendChild(form);
						index++;
						candidate.value = "";
						candidate.focus();
					})

			$("#interestInput").keypress(function(event) {
				if (event.which == 13) {
					document.getElementById("addInterest").click();
					event.preventDefault();
				}
			})

			function readURL(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader();
					reader.onload = function(event) {
						$('#userImage').attr('src', event.target.result);
					}

					reader.readAsDataURL(input.files[0]);
				}
			}

			$('#imageUpload').change(function() {
				readURL(this);
			});

		});

function removeInterest(interest) {
	console.log(interest);
	var inputElement = document.getElementById("input-" + interest);
	var liElement = document.getElementById("entry-" + interest);
	var ul = $("#interestsList")[0];
	ul.removeChild(liElement);
	ul.removeChild(inputElement);
}
