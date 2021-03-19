let brandForm = new Vue({
    el: '#brandForm',
    data: {
        form: {
            brandSeq: 0,
            useYn: true,
            brandNm: '',
            brandEngNm: '',
            dispYn: true,
            dispNo: 0,
            explain: '',

        }
    },
    methods: {
        updateInput: function (event) {
            this.form[event.target.name] = event.target.value
        },
        save: function () {
            console.log(this.form)
            axios({
                method: 'POST',
                url: 'http://localhost:8080/brands',
                data: this.form,
            }).then(function (response) {
                console.log(response);
            });
        }
    }
})