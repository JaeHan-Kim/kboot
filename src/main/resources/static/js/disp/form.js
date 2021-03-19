let form = new Vue({
    el: '#form',
    data: {
        form: {
            name: 'a',
            email: '',
            images: '',
        }
    },
    methods: {
        save: function () {
            axios({
                method: 'post',
                url: 'http://localhost:8080/api/save',
                data: this.form,
            }).then(function (response) {
                console.log(response);
            });

        },
        s3Save: function () {
            axios({
                method: 'post',
                url: 'http://localhost:8080/api/s3-file-upload',
                data: this.form,
            }).then(function (response) {
                console.log(response);
            });
        },
        fileSubmit: function (event) {
            let fileList = event.target.files;
            if (fileList) {
                const files = Array.from(event.target.files);
                Promise.all(files.map(file => {
                    return (new Promise((resolve, reject) => {
                        let reader = new FileReader();
                        reader.addEventListener('load', ev => {
                            resolve(ev.target.result);
                        });
                        reader.addEventListener('error', reject);
                        reader.readAsDataURL(file);
                    }));
                }))
                    .then(images => {
                        this.form.images = images;
                    }, error => {
                        console.error(error);
                    });
            }
        }
    }
});