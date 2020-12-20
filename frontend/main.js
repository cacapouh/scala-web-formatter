var app = new Vue({
  el: "#app",
  data:{
    scalaString: "",
    result: ""
  },
  methods: {
    onChange: function(event) {
      const data = {value: this.scalaString}

      axios
        .post("http://localhost:8080/format", data)
        .then(res => this.result = res.data);
    }
  }
})
