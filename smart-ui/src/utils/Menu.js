class Menu {
  constructor(name, path, hidden, children, component, meta, redirect) {
    this.name = name
    this.path = path
    this.hidden = hidden
    this.children = children
    this.component = component
    if (meta) {
      this.meta = meta
    }

    if (redirect) {
      this.redirect = redirect
    }
  }
}

export default Menu
