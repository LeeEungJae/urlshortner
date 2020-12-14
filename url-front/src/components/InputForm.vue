<template>
  <div class="input">
    <div
      class="carousel fade-carousel slide"
      data-ride="carousel"
      data-interval="4000"
      id="bs-carousel"
    >
      <div class="overlay"></div>

      <div class="carousel-inner">
        <div class="item">
          <div class="hero">
            <hgroup>
              <h1>URL SHORTNER</h1>
            </hgroup>
            <div>
              <input
                type="text"
                class="form-control"
                placeholder="Original URL"
                aria-label="Original URL"
                aria-describedby="basic-addon2"
                v-model="original_url"
              />
              <div v-if="flag == true">
                <b-card
                  v-if="ShortUrlResult.sucessFlag == true"
                  class="shorten-card "
                >
                  <div class="card-header">Shorten URL</div>
                  <div class="card-body">
                    <div class="row">
                      <div class="col-8 url">
                        <div class="alert alert-light" role="alert">
                          <a
                            :href="ShortUrlResult.shortUrl.shortenUrl"
                            class="alert-link"
                            >{{ ShortUrlResult.shortUrl.shortenUrl }}</a
                          >
                          <input
                            type="hidden"
                            id="shortenUrl"
                            :value="ShortUrlResult.shortUrl.shortenUrl"
                          />
                        </div>
                      </div>
                      <div class=" col-4 copy-btn">
                        <button
                          type="button"
                          class="btn btn-secondary btn-lg"
                          v-on:click="clickCopy"
                        >
                          Copy
                        </button>
                      </div>
                    </div>
                  </div>
                </b-card>
                <div
                  v-if="ShortUrlResult.sucessFlag == false"
                  class="invalid-url"
                >
                  <span>Invalid URL</span>
                </div>
              </div>

              <button
                class="btn btn-hero btn-lg"
                role="button"
                v-on:click="clickShorten"
              >
                Shorten
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      original_url: "",
      ShortUrlResult: null,
      flag: false,
    };
  },
  methods: {
    clickShorten() {
      this.$http
        .get("http://localhost:9000/api/shorten", {
          params: {
            url: this.original_url,
          },
        })
        .then((result) => {
          console.log(result.data);
          this.ShortUrlResult = result.data;
          this.original_url = "";
          this.flag = true;
        });
    },
    clickCopy() {
      let testingCodeToCopy = document.querySelector("#shortenUrl");
      testingCodeToCopy.setAttribute("type", "text");
      testingCodeToCopy.select();

      try {
        var successful = document.execCommand("copy");
        var msg = successful ? "successful" : "unsuccessful";
        alert("Shorten URL copied : " + msg);
      } catch (err) {
        alert("Oops, unable to copy");
      }

      testingCodeToCopy.setAttribute("type", "hidden");
      window.getSelection().removeAllRanges();
    },
  },
};
</script>
<style>
.fade-carousel {
  position: relative;
  height: 100vh;
}
.fade-carousel .carousel-inner .item {
  height: 100vh;
}

.hero {
  position: absolute;
  top: 50%;
  left: 50%;
  z-index: 3;
  color: #fff;
  text-align: center;
  text-shadow: 1px 1px 0 rgba(0, 0, 0, 0.75);
  -webkit-transform: translate3d(-50%, -50%, 0);
  -moz-transform: translate3d(-50%, -50%, 0);
  -ms-transform: translate3d(-50%, -50%, 0);
  -o-transform: translate3d(-50%, -50%, 0);
  transform: translate3d(-50%, -50%, 0);
}
.hero h1 {
  font-size: 6em;
  text-transform: uppercase;
  font-weight: bold;
  margin: 0;
  padding: 0;
}

.shorten-card {
  margin-top: 1em;
}

.shorten-card .card-header {
  color: #f5f5f5;
  background-color: #1abc9c;
  text-align: left;
}

.shorten-card .url {
  color: #080d15;
  font-size: em;
  text-align: left;
}

.shorten-card .copy-btn {
  text-align: right;
}

.invalid-url {
  color: #ff0000;
  margin-top: 0.3em;
}

.overlay {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 2;
  background-color: #080d15;
  opacity: 0.7;
}

/********************************/
/*          Custom Buttons      */
/********************************/
.btn.btn-lg {
  padding: 10px 40px;
}
.btn.btn-hero,
.btn.btn-hero:hover,
.btn.btn-hero:focus {
  color: #f5f5f5;
  background-color: #1abc9c;
  border-color: #1abc9c;
  outline: none;
  margin: 20px auto;
}

/********************************/
/*       Slides backgrounds     */
/********************************/
.fade-carousel {
  height: 100vh;
  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat;
  background-image: url(https://ununsplash.imgix.net/photo-1416339134316-0e91dc9ded92?q=75&fm=jpg&s=883a422e10fc4149893984019f63c818);
}
</style>
