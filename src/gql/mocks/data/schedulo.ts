import faker from '@faker-js/faker'
import { Schedulo } from '../../generatedTypes'

const schedulo: Array<Schedulo> = []
for (let i = 0; i < 30; i++) {
  const id = `${i + 1}`
  schedulo.push({
    __typename: 'Schedulo',
    _id: id,
    name: `Schedulo ${id}`,
    description: faker.lorem.paragraph(),
    logo: faker.image.business(),
  } as Schedulo)
}

export {
  schedulo
}
