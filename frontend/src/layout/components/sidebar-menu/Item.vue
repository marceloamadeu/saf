<template>
  <b-card no-body class="mb-1">
    <template v-if="menu.children">
      <b-card-header header-tag="header" role="tab">
        <b-button
          block
          v-b-toggle="'accordion-' + menu.name"
          variant="link"
          class="has-children"
        >
          <div class="menu-icon">
            <i :class="menu.icon"></i>
          </div>
          <span v-if="menu.children" class="menu-label">{{ menu.name }}</span>
          <b-badge v-if="menu.badge" pill :variant="menu.badge.variant">{{
            menu.badge.descricao
          }}</b-badge>
        </b-button>
      </b-card-header>
      <b-collapse
        v-if="menu.children"
        :id="accordion"
        :accordion="menu.name"
        role="tabpanel"
      >
        <SubItem :itens="menu.children" />
      </b-collapse>
    </template>

    <template v-if="!menu.children">
      <b-card-header header-tag="header" role="tab">
        <b-button block variant="link"  >
          <div class="menu-icon">
            <i :class="menu.icon"></i>
          </div>
          <router-link :to="menu.url" tag="span" class="menu-label">{{
            menu.name
          }}</router-link>
          <b-badge v-if="menu.badge" pill :variant="menu.badge.variant">{{
            menu.badge.descricao
          }}</b-badge>
        </b-button>
      </b-card-header>
    </template>
  </b-card>
</template>

<script>
import SubItem from './SubItem'
export default {
  components: {
    SubItem
  },
  props: {
    menu: Object
  },
  computed: {
    accordion () {
      return 'accordion-' + this.menu.name
    }
  },
  data () {
    return {
      teste: '0'

    }
  }

}
</script>
