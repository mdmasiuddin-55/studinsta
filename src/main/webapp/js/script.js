// Simple JS for alerts and interactions

// Show confirmation before posting a comment
function confirmComment() {
    return confirm("Are you sure you want to post this comment?");
}

// Show preview of image before upload
function previewImage(event, previewId) {
    const reader = new FileReader();
    reader.onload = function(){
        const output = document.getElementById(previewId);
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}
