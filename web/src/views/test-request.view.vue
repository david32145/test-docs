<template>
  <div id="test-request-page">
    <header-component />
    <subheader-with-title-component :title="title" />
    <main>
      <div></div>
      <div>
        <button @click="createNewSuite" class="btn-form btn-form-test-request">
          Nova Suite
        </button>
        <ul>
          <li v-for="suite in suites" :key="suite.id">
            <div class="suite-item">
              <img
                @click="deleteSuite(suite.id)"
                class="delete"
                src="@/assets/delete.svg"
              />
              <p>{{ suite.title }}</p>
              <span>{{ suite.description }}</span>
              <ul class="test-cases">
                <li v-for="cases in suite.testCases" :key="cases.id">
                  <div class="case-item">
                    <img
                      v-if="cases.testCaseStatus === 'NOT_PASSED'"
                      src="@/assets/closed_test_case.svg"
                      alt="icone"
                    />
                    <img
                      v-else-if="cases.testCaseStatus === 'PASSED'"
                      src="@/assets/check_green.svg"
                      alt="icone"
                    />
                    <img
                      v-else
                      src="@/assets/exclamation_icon.png"
                      alt="icone"
                    />
                    <div class="info">
                      <p>{{ cases.title }}</p>
                      <span>{{ cases.description }}</span>
                      <div
                        v-if="!cases.testCaseStatus"
                        class="test-cases-actions"
                      >
                        <button @click="approve(cases.id, suite.id)">
                          aprovar
                        </button>
                        <button @click="disapprove(cases.id, suite.id)">
                          reprovar
                        </button>
                      </div>
                    </div>
                    <img
                      v-if="!cases.testCaseStatus"
                      class="delete-test-case"
                      src="@/assets/close_red.svg"
                      alt=""
                    />
                  </div>
                </li>
              </ul>
              <div class="actions">
                <button @click="createNewTestCase(suite.id)">Novo</button>
              </div>
            </div>
          </li>
        </ul>
        <div class="bottom-buttons">
          <button @click="accept" class="btn-form">Aprovar</button>
          <button @click="reject" class="btn-form">Reprovar</button>
        </div>
      </div>
      <div></div>
    </main>
  </div>
</template>

<script>
import HeaderComponent from "@/components/header.component.vue";
import SubheaderWithTitleComponent from "@/components/subheader-with-title.component.vue";
import httpClient from "../providers/http-client";

export default {
  name: "test-request-page",
  components: {
    HeaderComponent,
    SubheaderWithTitleComponent,
  },
  data() {
    return {
      title: "Notas da versão",
      testRequest: {},
      suites: [],
    };
  },
  methods: {
    createNewSuite() {
      const { test_request_id, project_id } = this.$route.params;

      this.$router.push(
        `/project/${project_id}/testrequest/${test_request_id}/testsuite/new`
      );
    },
    deleteSuite(id) {
      httpClient
        .delete(`/test_suites/${id}`)
        .then(() => {
          this.suites = this.suites.filter((s) => s.id != id);
        })
        .catch(() => {
          alert("Não foi possível deletar a suite");
        });
    },
    approve(casesId, suiteId) {
      httpClient
        .put(`/test_suites/test_case/${casesId}/approve`)
        .then((response) => {
          const testCase = response.data;
          this.suites = this.suites.map((s) => {
            if (s.id != suiteId) {
              return s;
            }
            s.testCases = s.testCases.map((tc) => {
              if (tc.id != casesId) {
                return tc;
              }
              return testCase;
            });
            return s;
          });
        })
        .catch(() => {
          alert("Ocorreu um error ao aprovar seu caso de teste");
        });
    },
    disapprove(casesId, suiteId) {
      httpClient
        .put(`/test_suites/test_case/${casesId}/disapprove`)
        .then((response) => {
          const testCase = response.data;
          console.log(testCase);
          this.suites = this.suites.map((s) => {
            if (s.id != suiteId) {
              return s;
            }
            s.testCases = s.testCases.map((tc) => {
              if (tc.id != casesId) {
                return tc;
              }
              return testCase;
            });
            return s;
          });
        })
        .catch(() => {
          alert("Ocorreu um error ao aprovar seu caso de teste");
        });
    },
    createNewTestCase(suiteId) {
      const { test_request_id, project_id } = this.$route.params;

      this.$router.push(
        `/project/${project_id}/testrequest/${test_request_id}/testsuite/${suiteId}/testcase/new`
      );
    },
    accept() {
      const { test_request_id, project_id } = this.$route.params;
      httpClient.put(`/test_requests/${test_request_id}/accept`)
        .then(() => {
          this.$router.push(`/project/${project_id}`)
        })
        .catch(() => {
          alert("Error ao aprovar a solicitação de teste")
        })
    },
    reject() {
      const { test_request_id, project_id } = this.$route.params;
      httpClient.put(`/test_requests/${test_request_id}/accept`)
        .then(() => {
          this.$router.push(`/project/${project_id}`)
        })
        .catch(() => {
          alert("Error ao aprovar a solicitação de teste")
        })
    },
  },
  mounted() {
    const testRequestId = this.$route.params.test_request_id;
    httpClient
      .get(`/test_requests/${testRequestId}`)
      .then((response) => {
        this.testRequest = response.data;
        this.title = "Notas da versão " + this.testRequest.version;
      })
      .catch((e) => {
        console.log("Error ao carregar a solicitação de testes");
      });
    httpClient
      .get(`/test_requests/${testRequestId}/test_suites`)
      .then((response) => {
        this.suites = response.data;
      })
      .catch((e) => {
        console.log("Error ao carregar a solicitação de testes");
      });
  },
};
</script>

<style>
#test-request-page {
  display: flex;
  flex: 1;
  flex-direction: column;
  height: 100%;
}

#test-request-page main {
  display: grid;
  grid-template-columns: 1fr 700px 1fr;
  grid-template-areas: "a b c";
  flex: 1;
  background-color: var(--light-green);
  padding: 20px;
}

.suite-item {
  margin-bottom: 18px;
  background-color: #fff;
  min-height: 60px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 20px;
  position: relative;
}

.suite-item .delete {
  position: absolute;
  right: 20px;
  top: 20px;
  cursor: pointer;
  width: 24px;
  height: 24px;
}

.suite-item span {
  font-family: Roboto;
  font-weight: 300;
  margin-top: 6px;
  font-size: 1.3rem;
  line-height: 2rem;

  letter-spacing: 0.02em;
  margin-top: 12px;
  color: var(--dark-text);
}

.suite-item p {
  font-family: Roboto;
  font-weight: 400;
  font-size: 1.8rem;
  letter-spacing: 0.02em;
  color: var(--dark-text);
  margin-bottom: 12px;
  cursor: pointer;
}

.suite-item .actions {
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  width: 100%;
}

.suite-item .actions button {
  background: transparent;
  color: var(--bg-blue);
  cursor: pointer;
  font-size: 12px;
  text-transform: uppercase;
  border: none;
}

.test-cases {
  display: flex;
  margin-top: 12px;
  flex-direction: column;
  width: 100%;
}

.test-cases .case-item {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 25px;
  width: 100%;
}

.test-cases .case-item img {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.test-cases .case-item .info {
  display: flex;
  flex-direction: column;
}

.test-cases .case-item p {
  margin: 0;
  font-size: 16px;
  margin-bottom: 15px;
}

.test-cases .case-item span {
  margin: 0;
  font-size: 12px;
}

.test-cases-actions {
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
  /* justify-content: flex-end; */
}

.test-cases-actions button {
  background: transparent;
  border: none;
  text-transform: uppercase;
  color: green;
  cursor: pointer;
}

.test-cases-actions button + button {
  color: var(--bg-red);
  margin-left: 12px;
}

.delete-test-case {
  width: 22px !important;
  height: 22px !important;
  margin-left: auto;
  cursor: pointer;
}

.btn-form-test-request {
  width: 240px;
  margin-bottom: 20px;
  margin-left: auto;
}

.bottom-buttons {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-top: 20px;
}

.bottom-buttons button {
  margin-right: 10px;
}

.bottom-buttons button + button {
  margin-left: 10px;
  background-color: var(--bg-red);
}

.bottom-buttons button + button:hover {
  background-color: var(--bg-red);
  opacity: 0.9;
}
</style>